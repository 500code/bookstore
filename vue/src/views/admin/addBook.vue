<template>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" e>

        <el-form-item label="书名" prop="bname">
            <el-input v-model="ruleForm.bname" class="input"></el-input>
        </el-form-item>

        <el-form-item label="标签" prop="tagList">
            <el-tag
                    :key="tag"
                    v-for="tag in ruleForm.tagList"
                    closable
                    :disable-transitions="false"
                    @close="handleClose(tag)">
                {{ tag }}
            </el-tag>
            <el-input
                    class="input-new-tag"
                    v-if="inputVisible"
                    v-model="inputValue"
                    ref="saveTagInput"
                    size="small"
                    @keyup.enter="handleInputConfirm"
                    @blur="handleInputConfirm"
            >
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
        </el-form-item>

        <el-form-item label="价格" prop="money">
            <el-input v-model="ruleForm.money" class="input"></el-input>
        </el-form-item>

        <el-form-item label="数量" prop="total">
            <el-input-number v-model="ruleForm.total" @change="handleChange" :min="1" :max="10"></el-input-number>
            <!--      <el-input v-model.number="ruleForm.total" class="input"></el-input>-->
        </el-form-item>

        <el-form-item label="作者" prop="author">
            <el-input v-model="ruleForm.author" class="input"></el-input>
        </el-form-item>

        <el-form-item label="图片" prop="pic">
            <el-upload
                    action="aa"
                    list-type="picture-card"
                    :limit="1"
                    :http-request="uploadPic">
                <i class="el-icon-plus"></i>
            </el-upload>
        </el-form-item>

        <el-form-item label="简介" prop="desc">
            <el-input type="textarea" v-model="ruleForm.desc"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    import {ElMessage} from "element-plus";

    export default {
        name: "addBook",
        data() {
            return {
                inputVisible: false,
                inputValue: '',
                ruleForm: {
                    bname: '',  //书名
                    tagList: [],  //标签
                    money: '',   //价格
                    pic: "",   //图片
                    total: '', //总数量
                    desc: '',  //简介
                    author: ''  //作者
                },
                rules: {
                    bname: [
                        {required: true, message: '请输入书名', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    money:
                        [{required: true, message: '请输入金额', trigger: 'blur'}],
                    author: [{required: true, message: '请输入作者', trigger: 'blur'}],
                    pic: [{required: true, message: '请选择一张图片', trigger: 'blur'}],
                    desc: [
                        {required: true, message: '请填写活动形式', trigger: 'blur'}
                    ]
                }
            };
        },
            methods: {
                submitForm(formName) {
                    console.log(this.tag)
                    const that=this;
                    that.$refs[formName].validate((valid) => {
                        if (valid) {
                            const formData = new FormData();
                            formData.append("bname", that.ruleForm.bname)
                            formData.append("taglist",that.ruleForm.tagList)
                            formData.append("money", that.ruleForm.money)
                            formData.append("ppic", that.ruleForm.pic)
                            formData.append("total", that.ruleForm.total)
                            formData.append("bdesc", that.ruleForm.desc)
                            formData.append("author", that.ruleForm.author)
                            that.$http.post("http://localhost:8081/api/addBook", formData)
                                .then(res => {
                                    console.log(res)
                                    ElMessage.success("添加成功");
                                    that.$router.push("/admin/bookList")

                                    // console.log(that.$store.state.token)
                                })
                        } else {
                            console.log('error submit!!');
                            ElMessage.error("添加失败");
                            return false;
                        }
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                //关于tag
                handleClose(tag) {
                    this.ruleForm.tagList.splice(this.ruleForm.tagList.indexOf(tag), 1);
                    console.log(this.ruleForm.tagList)
                },
                showInput() {
                    this.inputVisible = true;
                    this.$nextTick(_ => {
                        this.$refs.saveTagInput.$refs.input.focus();
                        console.log(_)
                    });
                },
                handleInputConfirm() {
                    let inputValue = this.inputValue;
                    if (inputValue) {
                        this.ruleForm.tagList.push(inputValue);
                        console.log(this.ruleForm.tagList)
                    }
                    this.inputVisible = false;
                    this.inputValue = '';
                },
                //自定义上传图片
                uploadPic(obj) {
                    console.log(obj)
                    this.ruleForm.pic = obj.file
                }

            }
        }
</script>

<style>
    .el-tag + .el-tag {
        margin-left: 10px;
    }

    .button-new-tag {
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }

    .input-new-tag {
        width: 90px;
        margin-left: 10px;
        vertical-align: bottom;
    }

    .input {
        width: 300px;
    }
</style>
