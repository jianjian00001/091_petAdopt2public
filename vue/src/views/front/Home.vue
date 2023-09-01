<template>
  <div style="padding-bottom: 20px">
    <div style="margin: 10px 0">
      <div style="width: 100%; display: flex">
        <div style="width:100%">
          <video style="width: 100%" src="../../assets/animalvideo.mp4" muted="true" autoplay="autoplay" controls="controls" loop="loop"></video>
        </div>
      </div>
    </div>

    <div style="margin: 10px 0; padding: 20px; background-color: #fff; border-radius: 10px">
      <div style="padding: 10px 0; font-size: 28px; color: #67C23A">公告列表</div>
      <el-collapse v-model="active">
        <el-collapse-item  :name="index + ''" v-for="(item, index) in notices">
          <template slot="title">
            <span style="font-size: 18px; color: #666">{{ item.name }}</span>
          </template>
          <div style="color: #888">{{ item.content }}</div>
        </el-collapse-item>
      </el-collapse>
    </div>



    <div style="margin: 10px 0">
      <div style="padding: 20px 10px; font-size: 28px; background-color: #fff; border-radius: 10px; color: #3F5EFB">流浪宠物</div>
      <el-card  v-for="item in tableData" :key="item.id" style="margin: 10px 0">
        <div style="display: flex">
          <div style="width: 140px" @click="$router.push('/front/homeDetail?id=' + item.id)"> <img :src="item.img" alt="" style="width: 100%; height: 160px; border-radius: 10px"></div>
          <div style="padding-left: 50px; flex: 1">
            <div style="border-bottom: 1px solid #ddd; width: 100%; padding-bottom: 10px">
              <span style="font-size: 24px">{{ item.nickname }}</span>
              <span style="margin-left: 20px">{{ item.sex }}</span>
              <span style="margin-left: 20px">{{ item.age }}</span>
            </div>
            <div style="line-height: 30px">
              <div><b style="margin-right: 10px">所属种类：</b>{{ item.type }}</div>
              <div><b style="margin-right: 10px">活动范围：</b>{{ item.address }}</div>
              <div><b style="margin-right: 10px">身体状态：</b>{{ item.status }}</div>
              <div><b style="margin-right: 10px">其他描述：</b>{{ item.information }}</div>
            </div>
          </div>
        </div>
      </el-card>
    </div>

    <div style="padding: 10px; background-color: #fff; border-radius: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
export default {
  name: "FrontHome",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      notices: [],
      active: '0'
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/animal/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })

      this.request.get("/notice/front").then(res => {
        this.notices = res.data
      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>

<style>

</style>
