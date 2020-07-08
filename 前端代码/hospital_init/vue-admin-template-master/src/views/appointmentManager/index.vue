<template>
  <div>
    <el-table :data="tableData" border stripe>
      <el-table-column align="center" prop="aid" label="编号" />
      <el-table-column align="center" prop="dname" label="医生" />
      <el-table-column align="center" prop="pname" label="患者"/>
      <el-table-column align="center" prop="adescription" width="400px" label="症状"/>
      <el-table-column align="center" prop="acreatedate" label="创建日期" />
      <el-table-column align="center" prop="aeffectdate" label="生效时间" />
      <el-table-column align="center" prop="aexpiredate" label="失效时间" />
      <el-table-column align="center" label="是否接诊" >
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.flag === 3">已失效</el-tag>
          <el-tag v-if="scope.row.flag === 0">等待接诊</el-tag>
          <el-tag type="success" v-if="scope.row.flag === 1">接诊中</el-tag>
          <el-tag type="success" v-if="scope.row.flag === 2">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" width="250px" label="操作">
        <template slot-scope="scope">
          <el-button type="info" size="mini" @click="addAppointment(scope.row)">添加预约</el-button>
          <el-button type="info" size="mini" @click="updateAppointment(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="delAppointment(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="dataSize"
      ></el-pagination>
    </div>
    <!-- 添加病历模态框 -->
    <el-dialog title="添加预约信息" :visible.sync="dialogFormVisible" width="30%" height="40%">
      <el-form ref="addAppointmentData" :model="addAppointmentData" :rules="rules" label-width="80px">
        <el-form-item label="预约ID" :label-width="formLabelWidth" prop="aId">
          <span>{{addAppointmentData.aid}}</span>
        </el-form-item>
        <el-form-item label="医生姓名" :label-width="formLabelWidth" prop="dName">
          <el-input v-model="addAppointmentData.dname"></el-input>
        </el-form-item>
        <el-form-item label="症状" :label-width="formLabelWidth" prop="aDescription">
          <el-input v-model="addAppointmentData.adescription"></el-input>
        </el-form-item>
        <el-form-item label="生效时间" :label-width="formLabelWidth" prop="aEffectdate">
          <el-date-picker v-model="addAppointmentData.aeffectdate"></el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" :label-width="formLabelWidth" prop="aExpiredate">
          <el-date-picker v-model="addAppointmentData.aexpiredate"></el-date-picker>
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCancelBtn">取 消</el-button>
        <el-button type="primary" @click="addOkBtn('addAppointmentData')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改模态框 -->
    <el-dialog title="修改预约" :visible.sync="updateDig" width="30%" height="40%">
      <el-form ref="updateAppointmentData" :model="updateAppointmentData" :rules="rules" label-width="80px">
        <el-form-item label="预约ID" :label-width="formLabelWidth" prop="aId">
          <span>{{updateAppointmentData.aid}}</span>
        </el-form-item>
        <el-form-item label="医生姓名" :label-width="formLabelWidth" prop="dName">
          <el-input v-model="updateAppointmentData.dname"></el-input>
        </el-form-item>
        <el-form-item label="症状" :label-width="formLabelWidth" prop="aDescription">
          <el-input v-model="updateAppointmentData.adescription"></el-input>
        </el-form-item>
        <el-form-item label="生效时间" :label-width="formLabelWidth" prop="aEffectdate">
          <el-input v-model="addAppointmentData.aeffectdate"></el-input>
        </el-form-item>
        <el-form-item label="失效时间" :label-width="formLabelWidth" prop="aExpiredate">
          <el-input v-model="addAppointmentData.aexpiredate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateCancelBtn">取 消</el-button>
        <el-button type="primary" @click="updateOkBtn('updateAppointmentData')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addAppointment,addPatien,delAppointment,AppointmentList,updateAppointment } from '../../api/patient'
export default {
  name: '',

  data () {
    return {
      tableData:[{}],
      pageSize: 5,
      pageNum: 1,
      dataSize: 0,
      dialogFormVisible:false,
      updateDig:false,
      formLabelWidth: "80px",
      addAppointmentData: {
        aid: 0,
        did:0,
        dname: "医生1",
        pid: 0,
        pname: "",
        adescription: "",
        aeffectdate: "",
        aexpiredate: "",
      },
      updateAppointmentData: {
        aid: 0,
        did:0,
        dname: "",
        pid: 0,
        pname: "",
        adescription: "",
        aeffectdate: "",
        aexpiredate: ""
      },
      rules:{},
    }
  },
mounted() {
  this.getData()
},
watch: {
    pageSize: function(){
      this.getData()
    },
    pageNum: function(){
      this.getData()
    }
  },
  methods: {
    getData(){
      const data = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      AppointmentList(data).then(Response =>{
        this.tableData = Response.data.list
        this.dataSize = Response.data.total
      })
    },
    handleSizeChange(val) {
      this.pageSize = val
    },
    handleCurrentChange(val) {
      this.pageNum = val
    },
    addAppointment(row) {
      this.dialogFormVisible = true,
      this.addAppointmentData.aid = row.aid,
      this.addAppointmentData.pname = row.pname,
      this.addAppointmentData.pid=row.pid,
      this.addAppointmentData.did=row.did
    },
    addCancelBtn() {
      this.dialogFormVisible = false,
      this.addAppointmentData = {
        aid: 0,
        did:0,
        dname: "",
        pid: 0,
        pname: "",
        acreatedate: "",
        adescription: "",
        aeffectdate: "",
        aexpiredate: "",
        flag:0
      }
    },
      addOkBtn(formName) {
        this.$refs[formName].validate(valid =>{
          if(valid){
            const data = {
              did: this.addAppointmentData.did,
              dname: this.addAppointmentData.dname,
              pname: this.addAppointmentData.pname,
              adescription: this.addAppointmentData.adescription,
              aeffectdate: this.addAppointmentData.aeffectdate,
              aexpiredate: this.addAppointmentData.aexpiredate,
              pid:this.addAppointmentData.pid
            }
            addAppointment(data).then(Response =>{
                this.$message({
                  showClose: true,
                  message: '添加成功',
                  type: 'success'
              })
              this.dialogFormVisible = false
              this.getData()
            }).catch(error =>{
              this.$message({
                  showClose: true,
                  message: '添加失败',
                  type: 'error'
              })
            })
          }
        })
      },
      updateAppointment(row) {
        this.updateAppointmentData = {...row},
        this.updateDig = true
      },
      updateCancelBtn() {
        this.updateDig = false,
        this.updateAppointmentData = {
          did:0,
          dname: "",
          pid: 0,
          pname: "",
          adescription: "",
          aeffectdate: "",
          aexpiredate: "",
          flag:0
        }
      },
      updateOkBtn(formName) {
        this.$refs[formName].validate(valid => {
          if(valid){
            const data = {
              did: this.updateAppointmentData.did,
              dname: this.updateAppointmentData.dname,
              pname: this.updateAppointmentData.pname,
              adescription: this.updateAppointmentData.adescription,
              aeffectdate: this.updateAppointmentData.aeffectdate,
              aexpiredate: this.updateAppointmentData.aexpiredate,
              flag: this.updateAppointmentData.flag,
              aid: this.updateAppointmentData.aid
            }
            updateAppointment(data).then(Response =>{
              this.$message({
                  showClose: true,
                  message: '修改成功',
                  type: 'success'
              })
              this.updateDig = false
              this.getData()
            }).catch(error =>{
              this.$message({
                  showClose: true,
                  message: '修改失败',
                  type: 'error'
              })
            })
          }
        })
      },
      delAppointment(row){
        const data = {
        aid: row.aid
      };
      
      this.$confirm("此操作将永久删除，请问是否继续？", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      }).then(() => {
        if(this.tableData.length != 1 || this.pageNum != 0) {
          console.log("gggg")
          delAppointment(data).then(Response => {
            
            this.$message({
                  showClose: true,
                  message: "删除成功",
                  type: "success"
                });
                this.getData();
          }).catch(error => {
            
                this.$message({
                  showClose: true,
                  message: "删除失败",
                  type: "error"
                });
              });
        }else {
            this.$message({
              showClose: true,
              message: "至少保留一条数据",
              type: "error"
            });
          }
      })
      }
  },
  
}
</script>

<style scoped>

</style>

