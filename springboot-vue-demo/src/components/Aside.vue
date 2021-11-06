<template>
	<div>
		<el-menu
		        default-active="2"
				style="width: 200px; min-height: calc(100vh - 50px)"
		        class="el-menu-vertical-demo"
		      >
		        <el-menu-item index="1">
		          <el-icon><icon-menu /></el-icon>
		          <span>导航一</span>
		        </el-menu-item>
		        <el-menu-item index="2">
		          <el-icon><setting /></el-icon>
		          <span>导航二</span>
		        </el-menu-item>
				<el-sub-menu index="3-1">
					<template #title>导航三</template>
					<el-menu-item index="3-1-1">选项一</el-menu-item>
				</el-sub-menu>
		      </el-menu>
	</div>
</template>

<script>
</script>

<style>
</style>
