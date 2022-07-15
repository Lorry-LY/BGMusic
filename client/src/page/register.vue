<template>
    <div class="login_page fillcontain">
        <transition mode="in-out" name="form-fade">
            <section v-show="showLogin" class="form_contianer">
                <div class="manage_tip">
                    <p>BGMusic音乐平台 注册</p>
                </div>
                <el-form ref="registerForm" :model="registerForm" :rules="rules">
                    <el-form-item prop="name">
                        <el-input v-model="registerForm.name" placeholder="昵称"></el-input>
                    </el-form-item>
                    <el-form-item prop="adminName">
                        <el-input v-model="registerForm.adminName" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="registerForm.password" placeholder="密码" type="password"></el-input>
                    </el-form-item>
                    <el-form-item prop="password2">
                        <el-input v-model="registerForm.password2" placeholder="确认密码" type="password"></el-input>
                    </el-form-item>
                    <el-form-item prop="phone">
                        <el-input v-model="registerForm.phone" placeholder="手机号"></el-input>
                    </el-form-item>
                    <el-form-item prop="e_mail">
                        <el-input v-model="registerForm.e_mail" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button class="submit_btn" type="primary" @click="submitForm('registerForm')">登录</el-button>
                    </el-form-item>
                </el-form>
                <el-button type="primary" @click="gotoLogin">返回去登录</el-button>
            </section>
        </transition>
    </div>
</template>

<script>
import {register} from '@/api/getData'
import {mapActions, mapState} from 'vuex'

export default {
    data() {
        return {
            registerForm: {
                name: '',
                adminName: '',
                password: '',
                password2: '',
                phone: '',
                e_mail: '',
            },
            rules: {
                name: [
                    {required: true, message: '请输入昵称', trigger: 'blur'},
                ],
                adminName: [
                    {required: true, message: '请输入用户名', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ],
                password2: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入手机号', trigger: 'blur'},
                ],
                e_mail: [
                    {required: true, message: '请输入邮箱', trigger: 'blur'},
                ],
            },
            showLogin: false,
        }
    },
    mounted() {
        this.showLogin = true;
        if (!this.adminInfo.id) {
            // this.getAdminData()
        }
    },
    computed: {
        ...mapState(['adminInfo']),
    },
    methods: {
        ...mapActions(['getAdminData']),
        async submitForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    if (this.registerForm.password === this.registerForm.password2) {
                        const res = await register({
                            name: this.registerForm.name,
                            adminName: this.registerForm.adminName,
                            passwd: this.registerForm.password,
                            phone: this.registerForm.phone,
                            e_mail: this.registerForm.e_mail,
                        })
                        if (res.success === true) {
                            this.$message({
                                type: 'success',
                                message: '账号注册成功'
                            });
                            this.$router.push('/')
                        } else {
                            this.$message({
                                type: 'error',
                                message: res.message
                            });
                        }
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '两次密码不一样',
                            offset: 100
                        });
                    }
                } else {
                    this.$notify.error({
                        title: '错误',
                        message: '请输入正确的用户名密码',
                        offset: 100
                    });
                    return false;
                }
            });
        },
        gotoLogin() {
            this.$router.push('/')
        }
    },
    watch: {
        adminInfo: function (newValue) {
            if (newValue.id) {
                this.$message({
                    type: 'success',
                    message: '检测到您之前登录过，将自动登录'
                });
                this.$router.push('manage')
            }
        }
    }
}
</script>

<style lang="less" scoped>
@import '../style/mixin';

.login_page {
    background-color: #324057;
}

.el-button {
    width: 100%;
}

.manage_tip {
    position: absolute;
    width: 100%;
    top: -100px;
    left: 0;

    p {
        font-size: 34px;
        color: #fff;
    }
}

.form_contianer {
    .wh(320px, 450px);
    .ctp(320px, 450px);
    padding: 25px;
    border-radius: 5px;
    text-align: center;
    background-color: #fff;

    .submit_btn {
        width: 100%;
        font-size: 16px;
    }
}

.tip {
    font-size: 12px;
    color: red;
}

.form-fade-enter-active, .form-fade-leave-active {
    transition: all 1s;
}

.form-fade-enter, .form-fade-leave-active {
    transform: translate3d(0, -50px, 0);
    opacity: 0;
}
</style>
