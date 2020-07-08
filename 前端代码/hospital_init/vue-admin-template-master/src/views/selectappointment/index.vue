<template>
  <div>
    <el-table :data="tableData" border stripe>
      <el-table-column align="center" prop="dname" label="医生" />
      <el-table-column align="center" prop="pname" label="患者" />
      <el-table-column align="center" prop="adescription" width="500px" label="描述"/>
      <el-table-column align="center" prop="acreatedate" width="150px" label="创建时间" />
      <el-table-column align="center" prop="aeffectdate" label="生效时间" />
      <el-table-column align="center" prop="aexpiredate" label="失效时间" />
      <el-table-column align="center" prop="flag" label="是否接诊" />
      <el-table-column align="center" width="250px" label="操作">
        <template>
          <el-button type="info" size="mini" >详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    
  </div>
</template>

<script>
import { getAppointmentByDid } from '../../api/patient'
export default {
  name: '',

  data () {
    return {
      tableData:[{}],
      did: localStorage.getItem('role')
    }
  },
  mounted() {
    this.getData()
  },

  methods: {
    getData(){
      const data = {
        dId: this.did
      }
      getAppointmentByDid(data).then(Response => {
        this.tableData = Response.data.list
      })
    }
  }
}
</script>

<style scoped>

</style>
