<template>
  <div style="padding: 10px;">
	  <div style="margin: 10px 0;">
		  <el-button @click="add()">新增</el-button>
	  </div>
	  <div style="margin: 10px 0;">
		  <el-input v-model="search" placeholder="请输入内容" style="width: 20%;" />
		  <el-button style="margin-left: 5px;" @click="load()">搜索</el-button>
	  </div>
  <el-table :data="tableData" style="width: 100%">
	    <el-table-column
	      prop="id"
	      label="ID"
	      sortable>
	    </el-table-column>
		<el-table-column
		  prop="name"
		  label="名称">
		</el-table-column>
		<el-table-column
		  prop="sex"
		  label="性别">
		</el-table-column>
		<el-table-column
		  prop="age"
		  label="年龄">
		</el-table-column>
		<el-table-column
		  prop="socialSecurity"
		  label="社保编号">
		</el-table-column>
		<el-table-column
		  prop="company"
		  label="单位">
		</el-table-column>
		<el-table-column
		  prop="phone"
		  label="电话">
		</el-table-column>
		<el-table-column
		  prop="adress"
		  label="地址">
		</el-table-column>
		<el-table-column
		  prop="donmtime"
		  label="入院时间">
		</el-table-column>
		<el-table-column
		  prop="diagnosis"
		  label="诊断">
		</el-table-column>
		<el-table-column
		  prop="inpatientNumber"
		  label="住院号">
		</el-table-column>
		<el-table-column
		  prop="placeOfOccurrence"
		  label="发生经过">
		</el-table-column>
		<el-table-column
		  prop="declarant"
		  label="陈述人">
		</el-table-column>
		<el-table-column
		  prop="relationship"
		  label="患者关系">
		</el-table-column>
		<el-table-column
		  prop="preparer"
		  label="填表人">
		</el-table-column>
		<el-table-column
		  prop="patient"
		  label="患者签字">
		</el-table-column>
  </el-table>
  <div style="margin: 10px 0;">
		 <el-pagination
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		      :current-page="currentPage"
		      :page-sizes="[5, 10, 20]"
		      :page-size="pageSize"
		      layout="total, sizes, prev, pager, next, jumper"
		      :total="total">
		    </el-pagination>
		  <el-dialog
		    title="提示"
		    v-model="centerDialogVisible"
		    width="40%">
		    <el-form :model="form" label-width="120px" :rules="rules" ref="form">
			        <el-form-item label="名称" style="width: 80%">
			          <el-input v-model="form.name"></el-input>
			        </el-form-item>
					<el-form-item label="性别" style="width: 80%">
					   <el-radio v-model="form.sex" label="男">男</el-radio>
					   <el-radio v-model="form.sex" label="女">女</el-radio>
					   <el-radio v-model="form.sex" label="未知">未知</el-radio>
					</el-form-item>
					<el-form-item label="年龄" style="width: 80%">
					  <el-input v-model="form.age"></el-input>
					</el-form-item>
					<el-form-item label="社保编码" style="width: 80%">
					  <el-input v-model="form.socialSecurity"></el-input>
					</el-form-item>
					<el-form-item label="工作单位" style="width: 80%">
					  <el-input v-model="form.company"></el-input>
					</el-form-item>
					<el-form-item label="联系电话" style="width: 80%">
					  <el-input v-model="form.phone"></el-input>
					</el-form-item>
					<el-form-item label="家庭住址" style="width: 80%">
					  <el-input v-model="form.adress"></el-input>
					</el-form-item>
					<el-form-item label="入院时间" style="width: 80%">
					   <el-date-picker v-model="form.donmtime" value-format="YYYY-MM-DD" type="date" placeholder="选择日期" style="width: 80%;" clearable disabled></el-date-picker>
					</el-form-item>
					<el-form-item label="诊断" style="width: 80%">
					  <el-input v-model="form.diagnosis"></el-input>
					</el-form-item>
					<el-form-item label="住院号" style="width: 80%">
					  <el-input v-model="form.inpatientNumber"></el-input>
					</el-form-item>
					<el-form-item label="发生经过" style="width: 80%">
					  <el-input type="textarea" v-model="form.placeOfOccurrence"></el-input>
					</el-form-item>
					<el-form-item label="陈述人" style="width: 80%">
					  <el-input v-model="form.declarant"></el-input>
					</el-form-item>
					<el-form-item label="患者关系" style="width: 80%">
					            <el-select v-model="form.relationship">
					                  <el-option
					                    v-for="item in index"
					                    :key="item.value"
					                    :label="item.label"
					                    :value="item.value"
					                    :disabled="item.disabled"
					                  >
					                  </el-option>
					                </el-select>
					</el-form-item>
					<el-form-item label="填表人" style="width: 80%">
					  <el-input v-model="form.preparer"></el-input>
					</el-form-item>
					<el-form-item label="患者签名" style="width: 80%">
					  <el-input v-model="form.patient"></el-input>
					</el-form-item>
			</el-form>
		    <span slot="footer" class="dialog-footer">
		      <el-button @click="centerDialogVisible = false">取 消</el-button>
		      <el-button type="primary" @click="save()">确 定</el-button>
		    </span>
		  </el-dialog>
  </div>
  </div>
</template>

<script>
import request from "../utils/request.js";

export default {
  name: 'Home',
  data(){
	  return{
		  search:'',
		  form:{},
		  total:0,
		  currentPage:1,
		  tableData:[],
		  pageSize:10,
		  centerDialogVisible:false,
		  index:[
			  {
				  label:"丈夫",
				  value:"丈夫"
			  },
			  {
			  				  label:"妻子",
			  				  value:"妻子"
			  },
			  {
			  				  label:"子女",
			  				  value:"子女"
			  },
			  {
			  				  label:"父亲",
			  				  value:"父亲"
			  },
			  {
			  				  label:"同事",
			  				  value:"同事"
			  },
			  {
			  				  label:"朋友",
			  				  value:"朋友"
			  },
			  {
			  				  label:"丈夫",
			  				  value:"丈夫"
			  },
			  {
			  				  label:"亲戚",
			  				  value:"亲戚"
			  },
		  ]
	  }
  },
  created() {
  	this.load()
  },
  methods:{
	  load(){
		  request.get("/api/admission/select",{
			  params:{
				  pageNum: this.currentPage,
				  pageSize: this.pageSize,
				  search: this.search
			  }
		  }).then(res =>{
			  console.log(res)
			  this.tableData= res.data.records
			  this.total=res.data.total
		  })
	  },
	  add(){
		  this.centerDialogVisible=true
		  this.form={}
	  },
	  save(){
		  request.post("/api/admission/save",this.form).then(res =>{
			  console.log(res)
			  if(res.code==="0"){
				  this.$message({
					  type:"success",
					  message:"新增成功"
				  })
			  }else{
				  this.$message({
					  type:"error",
					  message:res.msg
				  })
			  }
			  this.load()
			  this.centerDialogVisible=false
		  })
	  },
	  handleSizeChange(pageSize){
		  this.pageSize=pageSize
		   this.load()
	  },
	  handleCurrentChange(pageNum){
		  this.currentPage=pageNum
		   this.load()
  },
	  
}
}
</script>
