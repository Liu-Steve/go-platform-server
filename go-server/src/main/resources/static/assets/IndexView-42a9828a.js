import{aD as c,x as i,J as d,B as n,z as o,A as a,aB as p,aC as m,ax as s}from"./index-ef60f7c8.js";import{E as _}from"./el-button-1fb10024.js";const f=n("div",null," 欢迎来到围棋平台 ",-1),v={__name:"IndexView",setup(x){const l=c(),r=()=>{localStorage.removeItem("Authorization"),p.defaults.headers.common.Authorization=null,m.success("已退出登录"),l.push("/")};return(u,e)=>{const t=_;return i(),d("div",null,[f,n("div",null,[o(t,{onClick:r,type:"danger",plain:""},{default:a(()=>[s("退出登录")]),_:1}),o(t,{onClick:e[0]||(e[0]=h=>u.$router.push("/game")),type:"success",plain:""},{default:a(()=>[s("开始下棋")]),_:1})])])}}};export{v as default};
