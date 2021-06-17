<template>
  <uHeader></uHeader>
</template>
<script>
  import uHeader from "@/components/u-header";

  export default {
    data(){
      return{
        key:""
      }
    },
    components:{
      uHeader
    },
    methods: {

    }
  }
</script>

<style scoped>
  .handle-box {

    margin-bottom: 20px;
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }
  .mr10 {
    margin-right: 10px;
  }

</style>
