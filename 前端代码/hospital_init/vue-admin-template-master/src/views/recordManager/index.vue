<template>
  <div>
    <el-table :data="tableData" border stripe>
      <el-table-column align="center" prop="pName" label="患者"/>
      <el-table-column align="center" prop="rPhone" label="手机号"/>
      <el-table-column align="center" prop="rSymptom" width="400px" label="症状" />
      <el-table-column align="center" prop="rDiagnosis" width="150px" label="诊断" />
      <el-table-column align="center" prop="rPrescription" label="处方" />
      <el-table-column align="center" prop="rCreatedate" label="创建日期" />
      <el-table-column align="center" width="250px" label="操作">
        <template slot-scope="scope">
          <el-button type="info" size="mini" @click="addRecord(scope.row)">添加病历</el-button>
          <el-button type="info" size="mini" @click="updateRecord(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="delRecord(scope.row)">删除</el-button>
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
    <el-dialog title="添加病历" :visible.sync="dialogFormVisible" width="20%" height="40%">
      <el-form ref="addRecordData" :model="addRecordData" :rules="rules" label-width="80px">
        <el-form-item label="症状" :label-width="formLabelWidth" prop="rSymptom">
          <el-input v-model="addRecordData.rSymptom"></el-input>
        </el-form-item>
        <el-form-item label="诊断" :label-width="formLabelWidth" prop="rDiagnosis">
          <el-input v-model="addRecordData.rDiagnosis"></el-input>
        </el-form-item>
        <el-form-item label="处方" :label-width="formLabelWidth" prop="rPrescription">
          <el-input v-model="addRecordData.rPrescription"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCancelBtn">取 消</el-button>
        <el-button type="primary" @click="addOkBtn('addRecordData')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 修改模态框 -->
    <el-dialog title="修改病历" :visible.sync="updateDig" width="20%" height="40%">
      <el-form ref="updateRecordData" :model="updateRecordData" :rules="rules" label-width="80px">
        <el-form-item label="处方" :label-width="formLabelWidth" prop="rPrescription">
          <el-input v-model="updateRecordData.rPrescription"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateCancelBtn">取 消</el-button>
        <el-button type="primary" @click="updateOkBtn('updateRecordData')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord,addRecord,updateRecord,delRecord } from '../../api/recordManager'
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
      addRecordData: {
        dId: 0,
        dName: "",
        pId: 0,
        pName: "",
        rCreatedate: "",
        rDiagnosis: "",
        rId: 0,
        rPhone: '',
        rPrescription: "",
        rSymptom: ""
      },
      updateRecordData: {
        dId: 0,
        dName: "",
        pId: 0,
        pName: "",
        rCreatedate: "",
        rDiagnosis: "",
        rId: 0,
        rPhone: '',
        rPrescription: "",
        rSymptom: ""
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
      listRecord(data).then(Response =>{
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
    addRecord(row) {
      this.dialogFormVisible = true,
      this.addRecordData.dId = row.dId,
      this.addRecordData.pName = row.pName,
      this.addRecordData.rPhone = row.rPhone
    },
    addCancelBtn() {
      this.dialogFormVisible = false,
      this.addRecordData = {
        dId: 0,
        dName: "",
        pId: 0,
        pName: "",
        rCreatedate: "",
        rDiagnosis: "",
        rId: 0,
        rPhone: 0,
        rPrescription: "",
        rSymptom: ""
      }
    },
      addOkBtn(formName) {
        this.$refs[formName].validate(valid =>{
          if(valid){
            const data = {
              dId: this.addRecordData.dId,
              dName: this.addRecordData.dName,
              pName: this.addRecordData.pName,
              rCreatedate: this.addRecordData.rCreatedate,
              rDiagnosis: this.addRecordData.rDiagnosis,
              rPrescription: this.addRecordData.rPrescription,
              rSymptom: this.addRecordData.rSymptom,
              rPhone: this.addRecordData.rPhone
            }
            addRecord(data).then(Response =>{
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
      updateRecord(row) {
        this.updateRecordData = {...row},
        this.updateDig = true
      },
      updateCancelBtn() {
        this.updateDig = false,
        this.updateRecordData = {
          dId: 0,
          dName: "",
          pId: 0,
          pName: "",
          rCreatedate: "",
          rDiagnosis: "",
          rId: 0,
          rPhone: 0,
          rPrescription: "",
          rSymptom: ""
        }
      },
      updateOkBtn(formName) {
        this.$refs[formName].validate(valid => {
          if(valid){
            const data = {
              dId: this.updateRecordData.dId,
              dName: this.updateRecordData.dName,
              pName: this.updateRecordData.pName,
              rCreatedate: this.updateRecordData.rCreatedate,
              rDiagnosis: this.updateRecordData.rDiagnosis,
              rPrescription: this.updateRecordData.rPrescription,
              rSymptom: this.updateRecordData.rSymptom,
              rPhone: this.updateRecordData.rPhone,
              rId: this.updateRecordData.rId
            }
            updateRecord(data).then(Response =>{
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
      delRecord(row){
        const data = {
        id: row.rId
      };
      
      this.$confirm("此操作将永久删除，请问是否继续？", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      }).then(() => {
        if(this.tableData.length != 1 || this.pageNum != 0) {
          console.log("gggg")
          delRecord(data).then(Response => {
            
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
