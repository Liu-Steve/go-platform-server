import{a6 as ee,G as A,h as k,z as pe,L as he,B as ye,v as K,U as E,N as Ue,ac as ne,g as L,ad as we,o as S,d as ke,w as xe,a as ae,n as x,u as b,I as _,c as C,a0 as $,aa as N,ae as R,i as Y,K as Me,t as Se,Y as Ce,j as V,e as Le,k as ze,F as Ke,$ as Ve,q as He}from"./index-c59bb034.js";import{h as Te,U,l as qe,m as Q,n as Ee,g as le,o as Oe,j as We}from"./index-ea33ca01.js";import{a8 as te,a9 as D,o as F,aa as Je,f as Ye,g as se,ab as Ae,t as Qe,c as Xe,n as Ze,ac as je,B as re,x as H,U as q,u as X,_ as Z,b as en,d as nn,w as an,V as Ie}from"./base-8418728c.js";import{c as Be,d as ln,U as ie,g as oe,e as ue,a as de,S as M,b as tn,f as sn}from"./el-input-49e67312.js";import{E as rn}from"./index-2e471c65.js";function on(e){return e}function un(e,n,l){switch(l.length){case 0:return e.call(n);case 1:return e.call(n,l[0]);case 2:return e.call(n,l[0],l[1]);case 3:return e.call(n,l[0],l[1],l[2])}return e.apply(n,l)}var dn=800,cn=16,fn=Date.now;function bn(e){var n=0,l=0;return function(){var a=fn(),s=cn-(a-l);if(l=a,s>0){if(++n>=dn)return arguments[0]}else n=0;return e.apply(void 0,arguments)}}function vn(e){return function(){return e}}var mn=te?function(e,n){return te(e,"toString",{configurable:!0,enumerable:!1,value:vn(n),writable:!0})}:on;const gn=mn;var pn=bn(gn);const hn=pn;var ce=Math.max;function yn(e,n,l){return n=ce(n===void 0?e.length-1:n,0),function(){for(var a=arguments,s=-1,t=ce(a.length-n,0),o=Array(t);++s<t;)o[s]=a[n+s];s=-1;for(var r=Array(n+1);++s<n;)r[s]=a[s];return r[n]=l(o),un(e,this,r)}}var fe=D?D.isConcatSpreadable:void 0;function wn(e){return F(e)||Be(e)||!!(fe&&e&&e[fe])}function Pe(e,n,l,a,s){var t=-1,o=e.length;for(l||(l=wn),s||(s=[]);++t<o;){var r=e[t];n>0&&l(r)?n>1?Pe(r,n-1,l,a,s):ln(s,r):a||(s[s.length]=r)}return s}function kn(e){var n=e==null?0:e.length;return n?Pe(e,1):[]}function xn(e){return hn(yn(e,void 0,kn),e+"")}var Sn="__lodash_hash_undefined__";function Cn(e){return this.__data__.set(e,Sn),this}function Ln(e){return this.__data__.has(e)}function G(e){var n=-1,l=e==null?0:e.length;for(this.__data__=new Je;++n<l;)this.add(e[n])}G.prototype.add=G.prototype.push=Cn;G.prototype.has=Ln;function Tn(e,n){for(var l=-1,a=e==null?0:e.length;++l<a;)if(n(e[l],l,e))return!0;return!1}function En(e,n){return e.has(n)}var On=1,An=2;function _e(e,n,l,a,s,t){var o=l&On,r=e.length,f=n.length;if(r!=f&&!(o&&f>r))return!1;var u=t.get(e),m=t.get(n);if(u&&m)return u==n&&m==e;var h=-1,y=!0,c=l&An?new G:void 0;for(t.set(e,n),t.set(n,e);++h<r;){var d=e[h],v=n[h];if(a)var g=o?a(v,d,h,n,e,t):a(d,v,h,e,n,t);if(g!==void 0){if(g)continue;y=!1;break}if(c){if(!Tn(n,function(p,w){if(!En(c,w)&&(d===p||s(d,p,l,a,t)))return c.push(w)})){y=!1;break}}else if(!(d===v||s(d,v,l,a,t))){y=!1;break}}return t.delete(e),t.delete(n),y}function In(e){var n=-1,l=Array(e.size);return e.forEach(function(a,s){l[++n]=[s,a]}),l}function Bn(e){var n=-1,l=Array(e.size);return e.forEach(function(a){l[++n]=a}),l}var Pn=1,_n=2,$n="[object Boolean]",Nn="[object Date]",Rn="[object Error]",Dn="[object Map]",Fn="[object Number]",Gn="[object RegExp]",Un="[object Set]",Mn="[object String]",zn="[object Symbol]",Kn="[object ArrayBuffer]",Vn="[object DataView]",be=D?D.prototype:void 0,z=be?be.valueOf:void 0;function Hn(e,n,l,a,s,t,o){switch(l){case Vn:if(e.byteLength!=n.byteLength||e.byteOffset!=n.byteOffset)return!1;e=e.buffer,n=n.buffer;case Kn:return!(e.byteLength!=n.byteLength||!t(new ie(e),new ie(n)));case $n:case Nn:case Fn:return Ye(+e,+n);case Rn:return e.name==n.name&&e.message==n.message;case Gn:case Mn:return e==n+"";case Dn:var r=In;case Un:var f=a&Pn;if(r||(r=Bn),e.size!=n.size&&!f)return!1;var u=o.get(e);if(u)return u==n;a|=_n,o.set(e,n);var m=_e(r(e),r(n),a,s,t,o);return o.delete(e),m;case zn:if(z)return z.call(e)==z.call(n)}return!1}var qn=1,Wn=Object.prototype,Jn=Wn.hasOwnProperty;function Yn(e,n,l,a,s,t){var o=l&qn,r=oe(e),f=r.length,u=oe(n),m=u.length;if(f!=m&&!o)return!1;for(var h=f;h--;){var y=r[h];if(!(o?y in n:Jn.call(n,y)))return!1}var c=t.get(e),d=t.get(n);if(c&&d)return c==n&&d==e;var v=!0;t.set(e,n),t.set(n,e);for(var g=o;++h<f;){y=r[h];var p=e[y],w=n[y];if(a)var j=o?a(w,p,y,n,e,t):a(p,w,y,e,n,t);if(!(j===void 0?p===w||s(p,w,l,a,t):j)){v=!1;break}g||(g=y=="constructor")}if(v&&!g){var I=e.constructor,B=n.constructor;I!=B&&"constructor"in e&&"constructor"in n&&!(typeof I=="function"&&I instanceof I&&typeof B=="function"&&B instanceof B)&&(v=!1)}return t.delete(e),t.delete(n),v}var Qn=1,ve="[object Arguments]",me="[object Array]",P="[object Object]",Xn=Object.prototype,ge=Xn.hasOwnProperty;function Zn(e,n,l,a,s,t){var o=F(e),r=F(n),f=o?me:ue(e),u=r?me:ue(n);f=f==ve?P:f,u=u==ve?P:u;var m=f==P,h=u==P,y=f==u;if(y&&de(e)){if(!de(n))return!1;o=!0,m=!1}if(y&&!m)return t||(t=new M),o||tn(e)?_e(e,n,l,a,s,t):Hn(e,n,f,l,a,s,t);if(!(l&Qn)){var c=m&&ge.call(e,"__wrapped__"),d=h&&ge.call(n,"__wrapped__");if(c||d){var v=c?e.value():e,g=d?n.value():n;return t||(t=new M),s(v,g,l,a,t)}}return y?(t||(t=new M),Yn(e,n,l,a,s,t)):!1}function $e(e,n,l,a,s){return e===n?!0:e==null||n==null||!se(e)&&!se(n)?e!==e&&n!==n:Zn(e,n,l,a,$e,s)}function jn(e,n){return e!=null&&n in Object(e)}function ea(e,n,l){n=Ae(n,e);for(var a=-1,s=n.length,t=!1;++a<s;){var o=Qe(n[a]);if(!(t=e!=null&&l(e,o)))break;e=e[o]}return t||++a!=s?t:(s=e==null?0:e.length,!!s&&sn(s)&&Xe(o,s)&&(F(e)||Be(e)))}function na(e,n){return e!=null&&ea(e,n,jn)}function aa(e,n){return $e(e,n)}function la(e,n,l){for(var a=-1,s=n.length,t={};++a<s;){var o=n[a],r=Ze(e,o);l(r,o)&&je(t,Ae(o,e),r)}return t}function ta(e,n){return la(e,n,function(l,a){return na(e,a)})}var sa=xn(function(e,n){return e==null?{}:ta(e,n)});const ra=sa,Ne={modelValue:{type:[Number,String,Boolean],default:void 0},label:{type:[String,Boolean,Number,Object]},indeterminate:Boolean,disabled:Boolean,checked:Boolean,name:{type:String,default:void 0},trueLabel:{type:[String,Number],default:void 0},falseLabel:{type:[String,Number],default:void 0},id:{type:String,default:void 0},controls:{type:String,default:void 0},border:Boolean,size:Te,tabindex:[String,Number],validateEvent:{type:Boolean,default:!0}},Re={[U]:e=>ee(e)||re(e)||H(e),change:e=>ee(e)||re(e)||H(e)},T=Symbol("checkboxGroupContextKey"),ia=({model:e,isChecked:n})=>{const l=A(T,void 0),a=k(()=>{var t,o;const r=(t=l==null?void 0:l.max)==null?void 0:t.value,f=(o=l==null?void 0:l.min)==null?void 0:o.value;return!q(r)&&e.value.length>=r&&!n.value||!q(f)&&e.value.length<=f&&n.value});return{isDisabled:qe(k(()=>(l==null?void 0:l.disabled.value)||a.value)),isLimitDisabled:a}},oa=(e,{model:n,isLimitExceeded:l,hasOwnLabel:a,isDisabled:s,isLabeledByFormItem:t})=>{const o=A(T,void 0),{formItem:r}=Q(),{emit:f}=ye();function u(d){var v,g;return d===e.trueLabel||d===!0?(v=e.trueLabel)!=null?v:!0:(g=e.falseLabel)!=null?g:!1}function m(d,v){f("change",u(d),v)}function h(d){if(l.value)return;const v=d.target;f("change",u(v.checked),d)}async function y(d){l.value||!a.value&&!s.value&&t.value&&(d.composedPath().some(p=>p.tagName==="LABEL")||(n.value=u([!1,e.falseLabel].includes(n.value)),await he(),m(n.value,d)))}const c=k(()=>(o==null?void 0:o.validateEvent)||e.validateEvent);return pe(()=>e.modelValue,()=>{c.value&&(r==null||r.validate("change").catch(d=>Ee()))}),{handleChange:h,onClickRoot:y}},ua=e=>{const n=K(!1),{emit:l}=ye(),a=A(T,void 0),s=k(()=>q(a)===!1),t=K(!1);return{model:k({get(){var r,f;return s.value?(r=a==null?void 0:a.modelValue)==null?void 0:r.value:(f=e.modelValue)!=null?f:n.value},set(r){var f,u;s.value&&E(r)?(t.value=((f=a==null?void 0:a.max)==null?void 0:f.value)!==void 0&&r.length>(a==null?void 0:a.max.value),t.value===!1&&((u=a==null?void 0:a.changeEvent)==null||u.call(a,r))):(l(U,r),n.value=r)}}),isGroup:s,isLimitExceeded:t}},da=(e,n,{model:l})=>{const a=A(T,void 0),s=K(!1),t=k(()=>{const u=l.value;return H(u)?u:E(u)?Ue(e.label)?u.map(ne).some(m=>aa(m,e.label)):u.map(ne).includes(e.label):u!=null?u===e.trueLabel:!!u}),o=le(k(()=>{var u;return(u=a==null?void 0:a.size)==null?void 0:u.value}),{prop:!0}),r=le(k(()=>{var u;return(u=a==null?void 0:a.size)==null?void 0:u.value})),f=k(()=>!!(n.default||e.label));return{checkboxButtonSize:o,isChecked:t,isFocused:s,checkboxSize:r,hasOwnLabel:f}},ca=(e,{model:n})=>{function l(){E(n.value)&&!n.value.includes(e.label)?n.value.push(e.label):n.value=e.trueLabel||!0}e.checked&&l()},De=(e,n)=>{const{formItem:l}=Q(),{model:a,isGroup:s,isLimitExceeded:t}=ua(e),{isFocused:o,isChecked:r,checkboxButtonSize:f,checkboxSize:u,hasOwnLabel:m}=da(e,n,{model:a}),{isDisabled:h}=ia({model:a,isChecked:r}),{inputId:y,isLabeledByFormItem:c}=Oe(e,{formItemContext:l,disableIdGeneration:m,disableIdManagement:s}),{handleChange:d,onClickRoot:v}=oa(e,{model:a,isLimitExceeded:t,hasOwnLabel:m,isDisabled:h,isLabeledByFormItem:c});return ca(e,{model:a}),{inputId:y,isLabeledByFormItem:c,isChecked:r,isDisabled:h,isFocused:o,checkboxButtonSize:f,checkboxSize:u,hasOwnLabel:m,model:a,handleChange:d,onClickRoot:v}},fa=["tabindex","role","aria-checked"],ba=["id","aria-hidden","name","tabindex","disabled","true-value","false-value"],va=["id","aria-hidden","disabled","value","name","tabindex"],ma=L({name:"ElCheckbox"}),ga=L({...ma,props:Ne,emits:Re,setup(e){const n=e,l=we(),{inputId:a,isLabeledByFormItem:s,isChecked:t,isDisabled:o,isFocused:r,checkboxSize:f,hasOwnLabel:u,model:m,handleChange:h,onClickRoot:y}=De(n,l),c=X("checkbox"),d=k(()=>[c.b(),c.m(f.value),c.is("disabled",o.value),c.is("bordered",n.border),c.is("checked",t.value)]),v=k(()=>[c.e("input"),c.is("disabled",o.value),c.is("checked",t.value),c.is("indeterminate",n.indeterminate),c.is("focus",r.value)]);return(g,p)=>(S(),ke(Le(!b(u)&&b(s)?"span":"label"),{class:x(b(d)),"aria-controls":g.indeterminate?g.controls:null,onClick:b(y)},{default:xe(()=>[ae("span",{class:x(b(v)),tabindex:g.indeterminate?0:void 0,role:g.indeterminate?"checkbox":void 0,"aria-checked":g.indeterminate?"mixed":void 0},[g.trueLabel||g.falseLabel?_((S(),C("input",{key:0,id:b(a),"onUpdate:modelValue":p[0]||(p[0]=w=>$(m)?m.value=w:null),class:x(b(c).e("original")),type:"checkbox","aria-hidden":g.indeterminate?"true":"false",name:g.name,tabindex:g.tabindex,disabled:b(o),"true-value":g.trueLabel,"false-value":g.falseLabel,onChange:p[1]||(p[1]=(...w)=>b(h)&&b(h)(...w)),onFocus:p[2]||(p[2]=w=>r.value=!0),onBlur:p[3]||(p[3]=w=>r.value=!1),onClick:p[4]||(p[4]=N(()=>{},["stop"]))},null,42,ba)),[[R,b(m)]]):_((S(),C("input",{key:1,id:b(a),"onUpdate:modelValue":p[5]||(p[5]=w=>$(m)?m.value=w:null),class:x(b(c).e("original")),type:"checkbox","aria-hidden":g.indeterminate?"true":"false",disabled:b(o),value:g.label,name:g.name,tabindex:g.tabindex,onChange:p[6]||(p[6]=(...w)=>b(h)&&b(h)(...w)),onFocus:p[7]||(p[7]=w=>r.value=!0),onBlur:p[8]||(p[8]=w=>r.value=!1),onClick:p[9]||(p[9]=N(()=>{},["stop"]))},null,42,va)),[[R,b(m)]]),ae("span",{class:x(b(c).e("inner"))},null,2)],10,fa),b(u)?(S(),C("span",{key:0,class:x(b(c).e("label"))},[Y(g.$slots,"default"),g.$slots.default?V("v-if",!0):(S(),C(Me,{key:0},[Se(Ce(g.label),1)],64))],2)):V("v-if",!0)]),_:3},8,["class","aria-controls","onClick"]))}});var pa=Z(ga,[["__file","/home/runner/work/element-plus/element-plus/packages/components/checkbox/src/checkbox.vue"]]);const ha=["name","tabindex","disabled","true-value","false-value"],ya=["name","tabindex","disabled","value"],wa=L({name:"ElCheckboxButton"}),ka=L({...wa,props:Ne,emits:Re,setup(e){const n=e,l=we(),{isFocused:a,isChecked:s,isDisabled:t,checkboxButtonSize:o,model:r,handleChange:f}=De(n,l),u=A(T,void 0),m=X("checkbox"),h=k(()=>{var c,d,v,g;const p=(d=(c=u==null?void 0:u.fill)==null?void 0:c.value)!=null?d:"";return{backgroundColor:p,borderColor:p,color:(g=(v=u==null?void 0:u.textColor)==null?void 0:v.value)!=null?g:"",boxShadow:p?`-1px 0 0 0 ${p}`:void 0}}),y=k(()=>[m.b("button"),m.bm("button",o.value),m.is("disabled",t.value),m.is("checked",s.value),m.is("focus",a.value)]);return(c,d)=>(S(),C("label",{class:x(b(y))},[c.trueLabel||c.falseLabel?_((S(),C("input",{key:0,"onUpdate:modelValue":d[0]||(d[0]=v=>$(r)?r.value=v:null),class:x(b(m).be("button","original")),type:"checkbox",name:c.name,tabindex:c.tabindex,disabled:b(t),"true-value":c.trueLabel,"false-value":c.falseLabel,onChange:d[1]||(d[1]=(...v)=>b(f)&&b(f)(...v)),onFocus:d[2]||(d[2]=v=>a.value=!0),onBlur:d[3]||(d[3]=v=>a.value=!1),onClick:d[4]||(d[4]=N(()=>{},["stop"]))},null,42,ha)),[[R,b(r)]]):_((S(),C("input",{key:1,"onUpdate:modelValue":d[5]||(d[5]=v=>$(r)?r.value=v:null),class:x(b(m).be("button","original")),type:"checkbox",name:c.name,tabindex:c.tabindex,disabled:b(t),value:c.label,onChange:d[6]||(d[6]=(...v)=>b(f)&&b(f)(...v)),onFocus:d[7]||(d[7]=v=>a.value=!0),onBlur:d[8]||(d[8]=v=>a.value=!1),onClick:d[9]||(d[9]=N(()=>{},["stop"]))},null,42,ya)),[[R,b(r)]]),c.$slots.default||c.label?(S(),C("span",{key:2,class:x(b(m).be("button","inner")),style:ze(b(s)?b(h):void 0)},[Y(c.$slots,"default",{},()=>[Se(Ce(c.label),1)])],6)):V("v-if",!0)],2))}});var Fe=Z(ka,[["__file","/home/runner/work/element-plus/element-plus/packages/components/checkbox/src/checkbox-button.vue"]]);const xa=en({modelValue:{type:nn(Array),default:()=>[]},disabled:Boolean,min:Number,max:Number,size:Te,label:String,fill:String,textColor:String,tag:{type:String,default:"div"},validateEvent:{type:Boolean,default:!0}}),Sa={[U]:e=>E(e),change:e=>E(e)},Ca=L({name:"ElCheckboxGroup"}),La=L({...Ca,props:xa,emits:Sa,setup(e,{emit:n}){const l=e,a=X("checkbox"),{formItem:s}=Q(),{inputId:t,isLabeledByFormItem:o}=Oe(l,{formItemContext:s}),r=async u=>{n(U,u),await he(),n("change",u)},f=k({get(){return l.modelValue},set(u){r(u)}});return Ke(T,{...ra(Ve(l),["size","min","max","disabled","validateEvent","fill","textColor"]),modelValue:f,changeEvent:r}),pe(()=>l.modelValue,()=>{l.validateEvent&&(s==null||s.validate("change").catch(u=>Ee()))}),(u,m)=>{var h;return S(),ke(Le(u.tag),{id:b(t),class:x(b(a).b("group")),role:"group","aria-label":b(o)?void 0:u.label||"checkbox-group","aria-labelledby":b(o)?(h=b(s))==null?void 0:h.labelId:void 0},{default:xe(()=>[Y(u.$slots,"default")]),_:3},8,["id","class","aria-label","aria-labelledby"])}}});var Ge=Z(La,[["__file","/home/runner/work/element-plus/element-plus/packages/components/checkbox/src/checkbox-group.vue"]]);const $a=an(pa,{CheckboxButton:Fe,CheckboxGroup:Ge});Ie(Fe);Ie(Ge);const i=He();var O=null;function Ta(e){try{"WebSocket"in window&&(O=new WebSocket(e),Oa(O,e))}catch(n){W(e),console.log(n)}}function Ea(){O.close(),i.ws_state=!1,J.reset()}function Oa(e,n){e.onopen=function(l){J.reset().start(),console.log("WebSocket已连接")},e.onclose=function(l){W(n),console.log("WebSocket连接已关闭")},e.onerror=function(l){W(n),console.log("WebSocket错误：",l)},e.onmessage=function(l){if(J.reset().start(),l.data!="Echo message: check"){let a=JSON.parse(l.data),s=Array(19*19).fill(0);if(a.mode===0||a.mode===1||a.mode===2||a.mode===3||a.mode===4){let t=a.message.board;for(let o=0;o<19;o++)for(let r=0;r<19;r++)t[o][r]===-1?s[o*19+r]=0:t[o][r]===0?s[o*19+r]=-1:t[o][r]===1&&(s[o*19+r]=1);i.jsonchessboard=JSON.parse(JSON.stringify(s))}switch(a.mode){case 0:if(i.chessboard=s,i.playerisblack=!i.playerisblack,!i.gamestart&&(i.gamestart=!0,i.showdialog=!1,i.blackplayer.id===i.userid)){let t=i.whiteplayer;i.whiteplayer=i.blackplayer,i.blackplayer=t}break;case 1:if(i.chessboard=s,i.playerisblack=!i.playerisblack,i.isdrop=!0,!i.gamestart&&(i.gamestart=!0,i.showdialog=!1,i.whiteplayer.id===i.userid)){let t=i.blackplayer;i.blackplayer=i.whiteplayer,i.whiteplayer=t}break;case 2:i.chessboard=s;break;case 3:i.chessboard=s,i.playerisblack=!i.playerisblack,i.isdrop=!0,rn({title:"对方选择停一手"});break;case 4:i.chessboard=s;break;case 5:i.showdialogend=!0,a.message.mode===0?(a.message.winner==="black"?i.winner="获胜方： "+i.blackplayer.name:a.message.winner==="white"?i.winner="获胜方： "+i.whiteplayer.name:a.message.winner==="equal"&&(i.winner="双方平局"),i.whitecount=a.message.white,i.blackcount=a.message.black,i.isnotsurrender=!0):a.message.mode===1&&(i.isnotsurrender=!1,i.winner="对方投降，你赢了！");break;case 10:i.roomowner.id=a.message.createUserId,i.roomowner.name=a.message.createUserName,i.roomplayer.id=a.message.secondUserId,i.roomplayer.id===-1?i.roomplayer.name="电脑玩家":We("/api/user/"+i.roomplayer.id,t=>{i.roomplayer.name=t.result.username});break;case 11:i.roomplayer.id="",i.roomplayer.name="",i.roomowner.id=i.userid,i.roomowner.name=i.username,i.isowner=!0,i.showdialog=!0;break}}}}function W(e){i.ws_state&&setTimeout(function(){Ta(e)},2e3)}window.onbeforeunload=function(){Ea()};var J={timeout:5e3,timeoutObj:null,serverTimeoutObj:null,reset:function(){return clearTimeout(this.timeoutObj),clearTimeout(this.serverTimeoutObj),this},start:function(){var e=this;this.timeoutObj=setTimeout(function(){O.send("check"),e.serverTimeoutObj=setTimeout(function(){O.close()},e.timeout)},this.timeout)}};export{$a as E,Pe as a,$e as b,Ea as c,na as h,on as i,yn as o,hn as s,Ta as w};