import{l as k,u as y,E as h,_ as v,w as b}from"./base-35d14e1b.js";import{i as C}from"./index-dfb80fbc.js";import{s as u,h as E,o,c as i,d as r,w as g,e as _,u as l,G as t,x as c,C as d}from"./index-10d3ea48.js";const w=k({type:{type:String,values:["primary","success","warning","info","danger","default"],default:"default"},underline:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},href:{type:String,default:""},icon:{type:C}}),B={click:a=>a instanceof MouseEvent},L=["href"],$=u({name:"ElLink"}),P=u({...$,props:w,emits:B,setup(a,{emit:p}){const s=a,n=y("link"),f=E(()=>[n.b(),n.m(s.type),n.is("disabled",s.disabled),n.is("underline",s.underline&&!s.disabled)]);function m(e){s.disabled||p("click",e)}return(e,I)=>(o(),i("a",{class:c(l(f)),href:e.disabled||!e.href?void 0:e.href,onClick:m},[e.icon?(o(),r(l(h),{key:0},{default:g(()=>[(o(),r(_(e.icon)))]),_:1})):t("v-if",!0),e.$slots.default?(o(),i("span",{key:1,class:c(l(n).e("inner"))},[d(e.$slots,"default")],2)):t("v-if",!0),e.$slots.icon?d(e.$slots,"icon",{key:2}):t("v-if",!0)],10,L))}});var S=v(P,[["__file","/home/runner/work/element-plus/element-plus/packages/components/link/src/link.vue"]]);const D=b(S);export{D as E};