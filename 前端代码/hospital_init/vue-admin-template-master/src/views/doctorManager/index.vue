<template>
  <div> 
    <el-table :data="tableData" border stripe>
      <el-table-column align="center" prop="dId" label="编号" />
      <el-table-column align="center" prop="dName" label="姓名" />
      <el-table-column align="center" prop="uUserName" label="登录名"/>
      <el-table-column align="center" prop="uPassWord" label="密码" />
      <el-table-column align="center" prop="dTitle" label="职称" />
      <el-table-column align="center" prop="dSex" label="性别" />
      <el-table-column align="center" prop="dSubject" label="科室" />
      <el-table-column align="center" prop="dEducation" label="学历" />
      <el-table-column align="center" width="250px" label="操作">
        <template slot-scope="scope">
          <el-button type="info" size="mini" @click="addDoctor(scope.row)" >添加医生</el-button>
          <el-button type="info" size="mini" @click="updateDoctor(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="delDoctor(scope.row)">删除</el-button>
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

    <!-- 添加医生模态框 -->
    <el-dialog title="添加医生" :visible.sync="dialogFormVisible" width="20%" height="40%">
      <el-form ref="addDoctorData" :model="addDoctorData" :rules="rules" label-width="80px">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="dName">
          <el-input v-model="addDoctorData.dName"></el-input>
        </el-form-item>
        <el-form-item label="登录名" :label-width="formLabelWidth" prop="uUserName">
          <el-input v-model="addDoctorData.uUserName"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth" prop="uPassWord">
          <el-input v-model="addDoctorData.uPassWord"></el-input>
        </el-form-item>
        <el-form-item label="职称">
          <el-select v-model="addDoctorData.dTitle" placeholder="请选择职称">
            <el-option label="主任医师" value="主任医生"></el-option>
            <el-option label="副主任医师" value="副主任医师"></el-option>
            <el-option label="主治医师" value="主治医师"></el-option>
            <el-option label="住院医师" value="住院医师"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科室">
          <el-select v-model="addDoctorData.dSubject" placeholder="请选择科室">
            <el-option label="博士研究生" value="博士研究生"></el-option>
            <el-option label="硕士研究生" value="硕士研究生"></el-option>
            <el-option label="妇产科" value="妇产科"></el-option>
            <el-option label="男科" value="男科"></el-option>
            <el-option label="肿瘤科" value="肿瘤科"></el-option>
            <el-option label="皮肤性病科" value="皮肤性病科"></el-option>
            <el-option label="中医科" value="中医科"></el-option>
            <el-option label="传染科" value="传染科"></el-option>
            <el-option label="护理科" value="护理科"></el-option>
            <el-option label="理疗科" value="理疗科"></el-option>
            <el-option label="设备科" value="设备科"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="addDoctorData.dSex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历">
          <el-select v-model="addDoctorData.dEducation" placeholder="请选择学历">
            <el-option label="博士研究生" value="博士研究生"></el-option>
            <el-option label="硕士研究生" value="硕士研究生"></el-option>
            <el-option label="本科" value="本科"></el-option>
            <el-option label="专科" value="专科"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCancelBtn">取 消</el-button>
        <el-button type="primary" @click="addOkBtn('addDoctorData')">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改模态框 -->
    <el-dialog title="修改医生" :visible.sync="updateDig" width="20%" height="40%">
      <el-form ref="updateDoctorData" :model="updateDoctorData" :rules="rules" label-width="80px">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="dName">
          <el-input v-model="updateDoctorData.dName"></el-input>
        </el-form-item>
        <el-form-item label="登录名" :label-width="formLabelWidth" prop="uUserName">
          <el-input v-model="updateDoctorData.uUserName"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth" prop="uPassWord">
          <el-input v-model="updateDoctorData.uPassWord"></el-input>
        </el-form-item>
        <el-form-item label="职称">
          <el-select v-model="updateDoctorData.dTitle" placeholder="请选择职称">
            <el-option label="主任医师" value="主任医生"></el-option>
            <el-option label="副主任医师" value="副主任医师"></el-option>
            <el-option label="主治医师" value="主治医师"></el-option>
            <el-option label="住院医师" value="住院医师"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="科室">
          <el-select v-model="updateDoctorData.dSubject" placeholder="请选择科室">
            <el-option label="博士研究生" value="博士研究生"></el-option>
            <el-option label="硕士研究生" value="硕士研究生"></el-option>
            <el-option label="妇产科" value="妇产科"></el-option>
            <el-option label="男科" value="男科"></el-option>
            <el-option label="肿瘤科" value="肿瘤科"></el-option>
            <el-option label="皮肤性病科" value="皮肤性病科"></el-option>
            <el-option label="中医科" value="中医科"></el-option>
            <el-option label="传染科" value="传染科"></el-option>
            <el-option label="护理科" value="护理科"></el-option>
            <el-option label="理疗科" value="理疗科"></el-option>
            <el-option label="设备科" value="设备科"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="updateDoctorData.dSex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历">
          <el-select v-model="updateDoctorData.dEducation" placeholder="请选择学历">
            <el-option label="博士研究生" value="内科"></el-option>
            <el-option label="硕士研究生" value="外科"></el-option>
            <el-option label="本科" value="本科"></el-option>
            <el-option label="专科" value="专科"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateCancelBtn">取 消</el-button>
        <el-button type="primary" @click="updateOkBtn('updateDoctorData')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord,addDoctor,updateDoctor,delDoctor } from '../../api/doctorManager'
import { getToken } from '@/utils/auth'
export default {
  name: '',

  data () {
    return {
      tableData:[{}],
      pageSize:5,
      pageNum:1,
      dataSize:0,
      //添加医生对话框
      dialogFormVisible:false,
      updateDig:false,
      formLabelWidth: '80px',
      rules: {
        dName:[
          {required: true , message: '请输入' , trgger: 'blur'},
          {min: 2 , max: 20 , message: '请输入2-20个字符' , trgger: 'blur'}
        ],
        uUserName:[
          {required: true , message: '请输入' , trgger: 'blur'},
          {validator: (rule, value, callback) =>{
            if (value) {
              if (/[\u4E00-\u9FA5]/g.test(value)) {
                callback(new Error('不能输入汉字!'));
              } else if (value.length<6){
                callback(new Error('不能小于6个字符'));
              }else if (value.length>18){
                callback(new Error('不能大于18个字符'));
              }else{
                callback()
              }
            }
            callback();
          }}
        ],
        uPassWord:[
          {required: true , message: '请输入' , trgger: 'blur'},
          {validator: (rule, value, callback) =>{
            if (value) {
              if (/[\u4E00-\u9FA5]/g.test(value)) {
                callback(new Error('不能输入汉字!'));
              } else if (value.length<6){
                callback(new Error('不能小于6个字符'));
              }else if (value.length>18){
                callback(new Error('不能大于18个字符'));
              }else{
                callback()
              }
            }
            callback();
          }}
        ],

      },
      addDoctorData:{
        dEducation: "",
        dId: 0,
        dName: "",
        dSex: "",
        dSubject: "",
        dTitle: "",
        uId: 0,
        uPassWord: "",
        uUserName: ""
      },
      updateDoctorData:{
        dEducation: "",
        dId: 0,
        dName: "",
        dSex: "",
        dSubject: "",
        dTitle: "",
        uId: 0,
        uPassWord: "",
        uUserName: ""   
      },
    
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
        pageSize: this.pageSize,
        pageNum: this.pageNum
      }
      listRecord(data).then(Response =>{
        console.log(getToken()+'ggg')
        console.log(getToken()+'GGG')
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
    handleChange(value) {
        console.log(value, this.$refs['myCascader'].currentLabels)
      },
    //添加医生按钮
    addDoctor(row){
      this.addDoctorData.uId = {...row.uId},
      this.dialogFormVisible = true
    },
    //修改按钮
    updateDoctor(row){
      this.updateDoctorData = { ...row }
      this.updateDig = true
    },
    
    //添加医生对话框取消按钮
    addCancelBtn() {
      this.dialogFormVisible = false ,
      this.addDoctorData = {
        dEducation: "",
        dName: "",
        dSex: "",
        dSubject: "",
        dTitle: "",
        uId: 0,
        uPassWord: "",
        uUserName: ""
      }
    },
    //修改医生对话框取消按钮
    updateCancelBtn() {
      this.updateDig = false ,
      this.addDoctorData = {
        dEducation: "",
        dName: "",
        dSex: "",
        dSubject: "",
        dTitle: "",
        uId: 0,
        uPassWord: "",
        uUserName: ""
      }
    },
    //添加医生对话框确定按钮
    addOkBtn(formName){
      this.$refs[formName].validate(valid => {
        if(valid){
          const data = {
            dName: this.addDoctorData.dName,
            uUserName: this.addDoctorData.uUserName,
            uPassWord: this.addDoctorData.uPassWord,
            dTitle: this.addDoctorData.dTitle,
            dSubject: this.addDoctorData.dSubject,
            dSex: this.addDoctorData.dSex,
            dEducation: this.addDoctorData.dEducation,
        }
        addDoctor(data).then(Response =>{
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          })
          this.dialogFormVisible = false
          this.getData()
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '添加失败',
            type: 'error'
          })
        })
        }
        
      })
    },
    //修改医生对话框确定按钮
    updateOkBtn(formName){
      this.$refs[formName].validate(valid => {
        if(valid){
          const data = {
            dId: this.updateDoctorData.dId,
            uId: this.updateDoctorData.uId,
            dName: this.updateDoctorData.dName,
            uUserName: this.updateDoctorData.uUserName,
            uPassWord: this.updateDoctorData.uPassWord,
            dTitle: this.updateDoctorData.dTitle,
            dSubject: this.updateDoctorData.dSubject,
            dSex: this.updateDoctorData.dSex,
            dEducation: this.updateDoctorData.dEducation,
          }
          updateDoctor(data).then( Response => {
            this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          })
          this.updateDig = false
          this.getData()
          }).catch(error => {
            this.$message({
            showClose: true,
            message: '修改失败,用户名不能重复',
            type: 'error'
          })
          })
        }
      })
    },
    //删除按钮
    delDoctor(row) {
      const data = {
        did: row.dId,
        uid: row.uId
      };
      
      this.$confirm("此操作将永久删除，请问是否继续？", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      }).then(() => {
        if(this.tableData.length != 1 || this.pageNum != 0) {
          console.log("gggg")
          delDoctor(data).then(Response => {
            
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
  }
}
</script>

<style scoped>
  .el-form-item{
    width: "200px";
  }
</style>
