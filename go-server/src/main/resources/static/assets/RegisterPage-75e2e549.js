import{r as b,s as c,v as U,x as C,E as F}from"./base-2d98388c.js";import{E as k}from"./el-message-554aaa51.js";import{E as z,a as B}from"./el-form-item-ec8b8f21.js";import{E as m,p as I,g as N,h as P,f as T}from"./index-c7515fd8.js";import{E as Z}from"./el-input-019c25e9.js";/* empty css                */import{I as q,k as A,o as D,c as L,a as l,b as e,w as a,u as d,L as w,A as u}from"./index-87a0bc49.js";import"./index-0743fc75.js";const R={style:{"text-align":"center",margin:"0 20px"}},$=l("div",{style:{"margin-top":"100px"}},[l("div",{style:{"font-size":"25px","font-weight":"bold"}},"新用户注册"),l("div",{style:{"font-size":"14px",color:"grey"}},"欢迎注册我们的围棋平台")],-1),M={style:{"margin-top":"50px"}},j={style:{"margin-top":"75px"}},G={style:{"margin-top":"20px"}},H=l("span",{style:{"font-size":"14px",color:"grey"}},"已有帐号?",-1),te={__name:"RegisterPage",setup(J){const t=q({id:0,username:"",password:"",password_repeat:"",email:"",code:"",salt:0,status:0,createdDate:"2023-10-12T13:09:58.634Z",updatedDate:"2023-10-12T13:09:58.634Z"}),x={username:[{validator:(n,s,o)=>{s===""?o(new Error("请输入用户名")):/^[\u4E00-\u9FFFa-zA-Z0-9]+$/.test(s)?o():o(new Error("用户名不能包含特殊字符，只能是中文/英文"))},trigger:["blur","change"]},{min:2,max:8,message:"用户名长度必须在2-8字符之间",trigger:["blur","change"]}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:6,max:16,message:"密码长度必须在6-16字符之间",trigger:["blur","change"]}],password_repeat:[{validator:(n,s,o)=>{s===""?o(new Error("请再次输入密码")):s!==t.password?o(new Error("两次输入的密码不一致")):o()},trigger:["blur","change"]}],email:[{required:!0,message:"请输入电子邮件",trigger:"blur"},{type:"email",message:"请输入合法的电子邮件地址",trigger:["blur","change"]}]},_=A(!1),y=(n,s)=>{n=="email"&&(_.value=s)},E=()=>{t.username?t.password?t.password_repeat!=t.password?m.warning("两次填写的密码需一致"):I("/api/user/register",{username:t.username,password:t.password,email:t.email},n=>{n.resultCode!=0?m.warning("账号已被注册！"):(m.success("注册成功!"),w.push("/"))}):m.warning("请填写密码"):m.warning("请填写用户名")};return(n,s)=>{const o=F,p=Z,i=B,f=P,g=N,V=T,v=z,h=k;return D(),L("div",R,[$,l("div",M,[e(v,{model:t,rules:x,onValidate:y},{default:a(()=>[e(i,{prop:"username"},{default:a(()=>[e(p,{modelValue:t.username,"onUpdate:modelValue":s[0]||(s[0]=r=>t.username=r),type:"text",placeholder:"用户名"},{prefix:a(()=>[e(o,null,{default:a(()=>[e(d(b))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(i,{prop:"password"},{default:a(()=>[e(p,{modelValue:t.password,"onUpdate:modelValue":s[1]||(s[1]=r=>t.password=r),type:"password",placeholder:"密码"},{prefix:a(()=>[e(o,null,{default:a(()=>[e(d(c))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(i,{prop:"password_repeat"},{default:a(()=>[e(p,{modelValue:t.password_repeat,"onUpdate:modelValue":s[2]||(s[2]=r=>t.password_repeat=r),type:"password",placeholder:"重复密码"},{prefix:a(()=>[e(o,null,{default:a(()=>[e(d(c))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(i,{prop:"email"},{default:a(()=>[e(p,{modelValue:t.email,"onUpdate:modelValue":s[3]||(s[3]=r=>t.email=r),type:"email",placeholder:"电子邮件"},{prefix:a(()=>[e(o,null,{default:a(()=>[e(d(U))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(i,{prop:"code"},{default:a(()=>[e(V,{gutter:10},{default:a(()=>[e(f,{span:18},{default:a(()=>[e(p,{modelValue:t.code,"onUpdate:modelValue":s[4]||(s[4]=r=>t.code=r),type:"email",placeholder:"验证码"},{prefix:a(()=>[e(o,null,{default:a(()=>[e(d(C))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(f,{span:6},{default:a(()=>[e(g,{type:"success",disabled:!_.value},{default:a(()=>[u("获取验证码")]),_:1},8,["disabled"])]),_:1})]),_:1})]),_:1})]),_:1},8,["model"])]),l("div",j,[e(g,{onClick:E,style:{width:"270px"},type:"warning",plain:""},{default:a(()=>[u(" 立即注册 ")]),_:1})]),l("div",G,[H,e(h,{onClick:s[5]||(s[5]=r=>d(w).push("/")),type:"primary",style:{translate:"0 -2px"}},{default:a(()=>[u(" 立即登录 ")]),_:1})])])}}};export{te as default};
