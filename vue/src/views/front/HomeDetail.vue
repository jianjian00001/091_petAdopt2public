<template>
  <div style="min-height: calc(100vh - 60px); margin: 10px 0">
    <div style="display: flex">
      <div style="width: 300px"><img style="width: 100%; border-radius: 10px" :src="animal.img" alt=""></div>

      <div style="padding-left: 30px; width: 500px">
        <el-card>
          <div style="font-size: 26px">{{ animal.nickname }}</div>
          <div style="line-height: 30px; margin-top: 20px">
            <div style="display: flex">
              <div style="width: 80px"><b>性别:</b></div>
              <div>{{ animal.sex }}</div>
            </div>

            <div style="display: flex">
              <div style="width: 80px"><b>种类:</b></div>
              <div>{{ animal.type }}</div>
            </div>

            <div style="display: flex">
              <div style="width: 80px"><b>年龄:</b></div>
              <div>{{ animal.age }}</div>
            </div>

            <div style="display: flex">
              <div style="width: 80px"><b>活动范围:</b></div>
              <div>{{ animal.address }}</div>
            </div>

            <div style="display: flex">
              <div style="width: 80px"><b>身体状态:</b></div>
              <div>{{ animal.status }}</div>
            </div>

            <div style="display: flex">
              <div style="width: 80px"><b>其他描述:</b></div>
              <div>{{ animal.information }}</div>
            </div>
            <div style="margin-top: 10px; color: red">
              <img src="../../assets/点赞.png" alt="" style="width: 30px; cursor: pointer" @click="praiseClick">
              <span style="position: relative; top: -5px">{{ animal.praise  }}</span>
            </div>
          </div>
        </el-card>
      </div>

    </div>

    <div style="padding: 30px 0">
      <div style="padding: 10px 0; font-size: 20px; border-bottom: 1px solid #666">评论</div>
      <div style="margin: 10px 0">
        <el-input size="mid" type="textarea" v-model="form.content" placeholder="请发表评论"></el-input>
        <div style="text-align: right; padding: 10px 0"><el-button type="primary" size="mid" @click="saveComment">评论</el-button></div>
      </div>

      <div style="margin: 30px 0">
        <div  v-for="item in comments" style="background-color: #fff; padding: 10px; border-radius: 10px">
          <div style="padding: 10px 0; display: flex; color: #666;  font-size: 14px">
            <div style="width: 100px; text-align: center" >
              <el-image :src="item.avatar" style="width: 50px; height: 50px; border-radius: 50%"></el-image>
            </div>
            <div style="flex: 1; line-height: 25px;">
              <div>
                <span style="color: #3F5EFB">{{item.user }}：</span>
                <span>{{ item.content }}</span>
              </div>
              <div style="font-size: 12px; margin-top: 5px; display: flex">
                <div style="flex: 1"><i class="el-icon-time"></i> <span>{{ item.time }}</span></div>
                <div style="width: 100px; text-align: right">
                  <el-button type="text" @click="handleAddComment(item.id)">回复</el-button>
                  <el-button type="text" @click="delComment(item.id)" style="color: red" v-if="user.nickname === item.user">删除</el-button>
                </div>
              </div>

              <div style="padding-left: 50px; line-height: 25px;">
                <div style="background-color: #eff; padding: 10px 20px" v-for="subitem in item.children">
                  <div>
                    <span style="color: #3F5EFB">{{ subitem.user }}：</span>
                    <span>{{ subitem.content }}</span>
                  </div>
                  <div style="font-size: 12px; margin-top: 5px; display: flex">
                    <div style="flex: 1"><i class="el-icon-time"></i> <span>{{ subitem.time }}</span></div>
                    <div style="width: 100px; text-align: right">
                      <el-button type="text" @click="delComment(subitem.id)" style="color: red" v-if="user.nickname === subitem.user">删除</el-button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
    <el-dialog title="回复" :visible.sync="dialogFormVisible" width="30%" :close-on-click-modal="false">
      <el-form label-width="80px" size="small" style="width: 90%">
        <el-form-item label="回复内容">
          <el-input type="textarea" v-model="form.contentr" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveComment">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "HomeDetail",
  data() {
    return {
      id: this.$route.query.id,
      animal: {},
      comments: [],
      form: {},
      dialogFormVisible: false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      praise: false
    }
  },
  created() {
    this.load()
    this.loadComment()
  },
  methods: {
    praiseClick() {
      if(this.praise) {
        return
      }
      this.animal.praise += 1
      this.request.post("/animal", this.animal).then(res => {
        this.load()
        this.praise = true
      })
    },
    load() {
      this.request.get("/animal/" + this.id).then(res => {
        this.animal = res.data
      })
    },
    loadComment() {
      this.request.get("/comment/article/1/" + this.id).then(res => {
        this.comments = res.data
      })
    },
    handleAddComment(pid) {
      this.dialogFormVisible = true
      this.form = {pid: pid}
    },
    saveComment() {
      if (!this.user.username) {
        this.$message.warning("请登录后评论")
        return
      }
      if (this.form.contentr) {
        this.form.content = this.form.contentr
      }
      if (!this.form.content) {
        this.$message.warning("请输入评论")
        return
      }
      this.form.type = 1
      this.form.articleId = this.id
      this.request.post("/comment", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("回复成功")
          this.dialogFormVisible = false
          this.form = {}  // 清空数据
          this.loadComment()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    delComment(id) {
      this.request.delete("/comment/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadComment()
        } else {
          this.$message.error("删除失败")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>