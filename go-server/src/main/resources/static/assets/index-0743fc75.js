import{ad as l}from"./base-2d98388c.js";import{g as t,j as u}from"./index-87a0bc49.js";const i=["class","style"],d=/^on[A-Z]/,m=(o={})=>{const{excludeListeners:a=!1,excludeKeys:e}=o,c=t(()=>((e==null?void 0:e.value)||[]).concat(i)),s=u();return s?t(()=>{var r;return l(Object.entries((r=s.proxy)==null?void 0:r.$attrs).filter(([n])=>!c.value.includes(n)&&!(a&&d.test(n))))}):t(()=>({}))};export{m as u};