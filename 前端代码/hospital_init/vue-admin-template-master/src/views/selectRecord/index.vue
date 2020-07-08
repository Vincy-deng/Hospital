<template>
  <div>
    <el-table :data="tableData" border stripe>
      <el-table-column align="center" prop="dName" label="医生" />
      <el-table-column align="center" prop="pName" label="患者" />
      <el-table-column align="center" prop="rSymptom" width="500px" label="症状"/>
      <el-table-column align="center" prop="rDiagnosis" width="200px" label="诊断" />
      <el-table-column align="center" prop="rPrescription" label="处方" />
      <el-table-column align="center" prop="rCreatedate" label="出生年月" />
      <el-table-column align="center" width="250px" label="操作">
        <template>
          <el-button type="info" size="mini" >详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    
  </div>
</template>

<script>
import { getRecordByUid } from '../../api/selectappointment'
export default {
  name: '',

  data () {
    return {
      tableData:[{}],
      naid: localStorage.getItem('role')
    }
  },
  mounted() {
    this.getData()
  },

  methods: {
    getData(){
      const data = {
        uId: this.naid
      }
      getRecordByUid(data).then(Response => {
        this.tableData = Response.data.list
      }).catch(error =>{
        this.$message({
          showClose: true,
          message: '该患者没有病历',
          type: 'warning'
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
