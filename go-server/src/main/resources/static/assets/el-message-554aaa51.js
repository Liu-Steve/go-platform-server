import{o as k,u as y,E as h,_ as v,w as b}from"./base-2d98388c.js";import{i as g}from"./index-c7515fd8.js";import{l as u,g as C,o,c as i,d as r,w as E,e as _,u as l,C as t,q as c,z as d}from"./index-87a0bc49.js";const w=k({type:{type:String,values:["primary","success","warning","info","danger","default"],default:"default"},underline:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},href:{type:String,default:""},icon:{type:g}}),B={click:a=>a instanceof MouseEvent},L=["href"],$=u({name:"ElLink"}),P=u({...$,props:w,emits:B,setup(a,{emit:p}){const s=a,n=y("link"),f=C(()=>[n.b(),n.m(s.type),n.is("disabled",s.disabled),n.is("underline",s.underline&&!s.disabled)]);function m(e){s.disabled||p("click",e)}return(e,z)=>(o(),i("a",{class:c(l(f)),href:e.disabled||!e.href?void 0:e.href,onClick:m},[e.icon?(o(),r(l(h),{key:0},{default:E(()=>[(o(),r(_(e.icon)))]),_:1})):t("v-if",!0),e.$slots.default?(o(),i("span",{key:1,class:c(l(n).e("inner"))},[d(e.$slots,"default")],2)):t("v-if",!0),e.$slots.icon?d(e.$slots,"icon",{key:2}):t("v-if",!0)],10,L))}});var S=v(P,[["__file","/home/runner/work/element-plus/element-plus/packages/components/link/src/link.vue"]]);const q=b(S);export{q as E};
