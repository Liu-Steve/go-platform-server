import{i as W,a as Ee,b as se,r as Ce,g as Le,c as we,d as ce,e as oe,f as ze,s as Te,u as he,h as ye,j as Se,E as M,k as Ne,l as Oe,m as $e,z as Re,n as Ae,o as Be,p as Ve,_ as _e,q as Me,w as ke,t as We,v as De}from"./base-e1d6743c.js";import{d as ie,m as me,r as T,e as Fe,s as Xe,c as x,w as de,n as be,o as Ie,a as E,b as fe,f as v,g as S,h as g,i as k,u as a,j as ve,k as Ye,l as O,p as N,F as re,q as He,t as Pe,v as je,x as Ke,y as ae,T as qe,z as Ze,A as Ge,B as Ue,C as Je,D as Qe,_ as et,E as tt,G as nt,H as at}from"./index-4b819a66.js";import{m as xe,u as K,E as j,a as st}from"./index-30c3aa27.js";const ot=(e,r)=>{if(!W||!e||!r)return!1;const t=e.getBoundingClientRect();let o;return r instanceof Element?o=r.getBoundingClientRect():o={top:0,right:window.innerWidth,bottom:window.innerHeight,left:0},t.top<o.bottom&&t.bottom>o.top&&t.right>o.left&&t.left<o.right};var rt=/\s/;function it(e){for(var r=e.length;r--&&rt.test(e.charAt(r)););return r}var lt=/^\s+/;function ut(e){return e&&e.slice(0,it(e)+1).replace(lt,"")}var pe=0/0,ct=/^[-+]0x[0-9a-f]+$/i,dt=/^0b[01]+$/i,ft=/^0o[0-7]+$/i,vt=parseInt;function ge(e){if(typeof e=="number")return e;if(Ee(e))return pe;if(se(e)){var r=typeof e.valueOf=="function"?e.valueOf():e;e=se(r)?r+"":r}if(typeof e!="string")return e===0?e:+e;e=ut(e);var t=dt.test(e);return t||ft.test(e)?vt(e.slice(2),t?2:8):ct.test(e)?pe:+e}var mt=function(){return Ce.Date.now()};const le=mt;var pt="Expected a function",gt=Math.max,wt=Math.min;function ht(e,r,t){var o,c,b,l,w,_,I=0,C=!1,h=!1,m=!0;if(typeof e!="function")throw new TypeError(pt);r=ge(r)||0,se(t)&&(C=!!t.leading,h="maxWait"in t,b=h?gt(ge(t.maxWait)||0,r):b,m="trailing"in t?!!t.trailing:m);function p(u){var y=o,A=c;return o=c=void 0,I=u,l=e.apply(A,y),l}function d(u){return I=u,w=setTimeout(R,r),C?p(u):l}function D(u){var y=u-_,A=u-I,H=r-y;return h?wt(H,b-A):H}function $(u){var y=u-_,A=u-I;return _===void 0||y>=r||y<0||h&&A>=b}function R(){var u=le();if($(u))return Y(u);w=setTimeout(R,D(u))}function Y(u){return w=void 0,m&&o?p(u):(o=c=void 0,l)}function q(){w!==void 0&&clearTimeout(w),I=0,o=_=c=w=void 0}function F(){return w===void 0?l:Y(le())}function X(){var u=le(),y=$(u);if(o=arguments,c=this,_=u,y){if(w===void 0)return d(_);if(h)return clearTimeout(w),w=setTimeout(R,r),p(_)}return w===void 0&&(w=setTimeout(R,r)),l}return X.cancel=q,X.flush=F,X}var yt="Expected a function";function ue(e,r,t){var o=!0,c=!0;if(typeof e!="function")throw new TypeError(yt);return se(t)&&(o="leading"in t?!!t.leading:o,c="trailing"in t?!!t.trailing:c),ht(e,r,{leading:o,maxWait:r,trailing:c})}const _t=(e,r)=>{if(!W)return!1;const t={undefined:"overflow",true:"overflow-y",false:"overflow-x"}[String(r)],o=Le(e,t);return["scroll","auto","overlay"].some(c=>o.includes(c))},kt=(e,r)=>{if(!W)return;let t=e;for(;t;){if([window,document,document.documentElement].includes(t))return window;if(_t(t,r))return t;t=t.parentNode}return t},bt=we({urlList:{type:ce(Array),default:()=>xe([])},zIndex:{type:Number},initialIndex:{type:Number,default:0},infinite:{type:Boolean,default:!0},hideOnClickModal:Boolean,teleported:Boolean,closeOnPressEscape:{type:Boolean,default:!0},zoomRate:{type:Number,default:1.2}}),It={close:()=>!0,switch:e=>oe(e),rotate:e=>oe(e)},xt=["src"],Et=ie({name:"ElImageViewer"}),Ct=ie({...Et,props:bt,emits:It,setup(e,{expose:r,emit:t}){const o=e,c={CONTAIN:{name:"contain",icon:me(ze)},ORIGINAL:{name:"original",icon:me(Te)}},{t:b}=he(),l=ye("image-viewer"),{nextZIndex:w}=Se(),_=T(),I=T([]),C=Fe(),h=T(!0),m=T(o.initialIndex),p=Xe(c.CONTAIN),d=T({scale:1,deg:0,offsetX:0,offsetY:0,enableTransition:!1}),D=x(()=>{const{urlList:n}=o;return n.length<=1}),$=x(()=>m.value===0),R=x(()=>m.value===o.urlList.length-1),Y=x(()=>o.urlList[m.value]),q=x(()=>[l.e("btn"),l.e("prev"),l.is("disabled",!o.infinite&&$.value)]),F=x(()=>[l.e("btn"),l.e("next"),l.is("disabled",!o.infinite&&R.value)]),X=x(()=>{const{scale:n,deg:i,offsetX:f,offsetY:L,enableTransition:B}=d.value;let z=f/n,V=L/n;switch(i%360){case 90:case-270:[z,V]=[V,-z];break;case 180:case-180:[z,V]=[-z,-V];break;case 270:case-90:[z,V]=[-V,z];break}const P={transform:`scale(${n}) rotate(${i}deg) translate(${z}px, ${V}px)`,transition:B?"transform .3s":""};return p.value.name===c.CONTAIN.name&&(P.maxWidth=P.maxHeight="100%"),P}),u=x(()=>oe(o.zIndex)?o.zIndex:w());function y(){H(),t("close")}function A(){const n=ue(f=>{switch(f.code){case j.esc:o.closeOnPressEscape&&y();break;case j.space:ee();break;case j.left:te();break;case j.up:s("zoomIn");break;case j.right:ne();break;case j.down:s("zoomOut");break}}),i=ue(f=>{const L=f.deltaY||f.deltaX;s(L<0?"zoomIn":"zoomOut",{zoomRate:o.zoomRate,enableTransition:!1})});C.run(()=>{K(document,"keydown",n),K(document,"wheel",i)})}function H(){C.stop()}function U(){h.value=!1}function J(n){h.value=!1,n.target.alt=b("el.image.error")}function Q(n){if(h.value||n.button!==0||!_.value)return;d.value.enableTransition=!1;const{offsetX:i,offsetY:f}=d.value,L=n.pageX,B=n.pageY,z=ue(P=>{d.value={...d.value,offsetX:i+P.pageX-L,offsetY:f+P.pageY-B}}),V=K(document,"mousemove",z);K(document,"mouseup",()=>{V()}),n.preventDefault()}function Z(){d.value={scale:1,deg:0,offsetX:0,offsetY:0,enableTransition:!1}}function ee(){if(h.value)return;const n=Me(c),i=Object.values(c),f=p.value.name,B=(i.findIndex(z=>z.name===f)+1)%n.length;p.value=c[n[B]],Z()}function G(n){const i=o.urlList.length;m.value=(n+i)%i}function te(){$.value&&!o.infinite||G(m.value-1)}function ne(){R.value&&!o.infinite||G(m.value+1)}function s(n,i={}){if(h.value)return;const{zoomRate:f,rotateDeg:L,enableTransition:B}={zoomRate:o.zoomRate,rotateDeg:90,enableTransition:!0,...i};switch(n){case"zoomOut":d.value.scale>.2&&(d.value.scale=Number.parseFloat((d.value.scale/f).toFixed(3)));break;case"zoomIn":d.value.scale<7&&(d.value.scale=Number.parseFloat((d.value.scale*f).toFixed(3)));break;case"clockwise":d.value.deg+=L,t("rotate",d.value.deg);break;case"anticlockwise":d.value.deg-=L,t("rotate",d.value.deg);break}d.value.enableTransition=B}return de(Y,()=>{be(()=>{const n=I.value[0];n!=null&&n.complete||(h.value=!0)})}),de(m,n=>{Z(),t("switch",n)}),Ie(()=>{var n,i;A(),(i=(n=_.value)==null?void 0:n.focus)==null||i.call(n)}),r({setActiveItem:G}),(n,i)=>(E(),fe(Ze,{to:"body",disabled:!n.teleported},[v(qe,{name:"viewer-fade",appear:""},{default:S(()=>[g("div",{ref_key:"wrapper",ref:_,tabindex:-1,class:k(a(l).e("wrapper")),style:ve({zIndex:a(u)})},[g("div",{class:k(a(l).e("mask")),onClick:i[0]||(i[0]=Ye(f=>n.hideOnClickModal&&y(),["self"]))},null,2),O(" CLOSE "),g("span",{class:k([a(l).e("btn"),a(l).e("close")]),onClick:y},[v(a(M),null,{default:S(()=>[v(a(Ne))]),_:1})],2),O(" ARROW "),a(D)?O("v-if",!0):(E(),N(re,{key:0},[g("span",{class:k(a(q)),onClick:te},[v(a(M),null,{default:S(()=>[v(a(Oe))]),_:1})],2),g("span",{class:k(a(F)),onClick:ne},[v(a(M),null,{default:S(()=>[v(a($e))]),_:1})],2)],64)),O(" ACTIONS "),g("div",{class:k([a(l).e("btn"),a(l).e("actions")])},[g("div",{class:k(a(l).e("actions__inner"))},[v(a(M),{onClick:i[1]||(i[1]=f=>s("zoomOut"))},{default:S(()=>[v(a(Re))]),_:1}),v(a(M),{onClick:i[2]||(i[2]=f=>s("zoomIn"))},{default:S(()=>[v(a(Ae))]),_:1}),g("i",{class:k(a(l).e("actions__divider"))},null,2),v(a(M),{onClick:ee},{default:S(()=>[(E(),fe(He(a(p).icon)))]),_:1}),g("i",{class:k(a(l).e("actions__divider"))},null,2),v(a(M),{onClick:i[3]||(i[3]=f=>s("anticlockwise"))},{default:S(()=>[v(a(Be))]),_:1}),v(a(M),{onClick:i[4]||(i[4]=f=>s("clockwise"))},{default:S(()=>[v(a(Ve))]),_:1})],2)],2),O(" CANVAS "),g("div",{class:k(a(l).e("canvas"))},[(E(!0),N(re,null,Pe(n.urlList,(f,L)=>je((E(),N("img",{ref_for:!0,ref:B=>I.value[L]=B,key:f,src:f,style:ve(a(X)),class:k(a(l).e("img")),onLoad:U,onError:J,onMousedown:Q},null,46,xt)),[[Ke,L===m.value]])),128))],2),ae(n.$slots,"default")],6)]),_:3})],8,["disabled"]))}});var Lt=_e(Ct,[["__file","/home/runner/work/element-plus/element-plus/packages/components/image-viewer/src/image-viewer.vue"]]);const zt=ke(Lt),Tt=we({hideOnClickModal:Boolean,src:{type:String,default:""},fit:{type:String,values:["","contain","cover","fill","none","scale-down"],default:""},loading:{type:String,values:["eager","lazy"]},lazy:Boolean,scrollContainer:{type:ce([String,Object])},previewSrcList:{type:ce(Array),default:()=>xe([])},previewTeleported:Boolean,zIndex:{type:Number},initialIndex:{type:Number,default:0},infinite:{type:Boolean,default:!0},closeOnPressEscape:{type:Boolean,default:!0},zoomRate:{type:Number,default:1.2}}),St={load:e=>e instanceof Event,error:e=>e instanceof Event,switch:e=>oe(e),close:()=>!0,show:()=>!0},Nt=["src","loading"],Ot={key:0},$t=ie({name:"ElImage",inheritAttrs:!1}),Rt=ie({...$t,props:Tt,emits:St,setup(e,{emit:r}){const t=e;let o="";const{t:c}=he(),b=ye("image"),l=Ge(),w=st(),_=T(),I=T(!1),C=T(!0),h=T(!1),m=T(),p=T(),d=W&&"loading"in HTMLImageElement.prototype;let D,$;const R=x(()=>[b.e("inner"),F.value&&b.e("preview"),C.value&&b.is("loading")]),Y=x(()=>l.style),q=x(()=>{const{fit:s}=t;return W&&s?{objectFit:s}:{}}),F=x(()=>{const{previewSrcList:s}=t;return Array.isArray(s)&&s.length>0}),X=x(()=>{const{previewSrcList:s,initialIndex:n}=t;let i=n;return n>s.length-1&&(i=0),i}),u=x(()=>t.loading==="eager"?!1:!d&&t.loading==="lazy"||t.lazy),y=()=>{W&&(C.value=!0,I.value=!1,_.value=t.src)};function A(s){C.value=!1,I.value=!1,r("load",s)}function H(s){C.value=!1,I.value=!0,r("error",s)}function U(){ot(m.value,p.value)&&(y(),Z())}const J=We(U,200,!0);async function Q(){var s;if(!W)return;await be();const{scrollContainer:n}=t;De(n)?p.value=n:Je(n)&&n!==""?p.value=(s=document.querySelector(n))!=null?s:void 0:m.value&&(p.value=kt(m.value)),p.value&&(D=K(p,"scroll",J),setTimeout(()=>U(),100))}function Z(){!W||!p.value||!J||(D==null||D(),p.value=void 0)}function ee(s){if(s.ctrlKey){if(s.deltaY<0)return s.preventDefault(),!1;if(s.deltaY>0)return s.preventDefault(),!1}}function G(){F.value&&($=K("wheel",ee,{passive:!1}),o=document.body.style.overflow,document.body.style.overflow="hidden",h.value=!0,r("show"))}function te(){$==null||$(),document.body.style.overflow=o,h.value=!1,r("close")}function ne(s){r("switch",s)}return de(()=>t.src,()=>{u.value?(C.value=!0,I.value=!1,Z(),Q()):y()}),Ie(()=>{u.value?Q():y()}),(s,n)=>(E(),N("div",{ref_key:"container",ref:m,class:k([a(b).b(),s.$attrs.class]),style:ve(a(Y))},[I.value?ae(s.$slots,"error",{key:0},()=>[g("div",{class:k(a(b).e("error"))},Qe(a(c)("el.image.error")),3)]):(E(),N(re,{key:1},[_.value!==void 0?(E(),N("img",Ue({key:0},a(w),{src:_.value,loading:s.loading,style:a(q),class:a(R),onClick:G,onLoad:A,onError:H}),null,16,Nt)):O("v-if",!0),C.value?(E(),N("div",{key:1,class:k(a(b).e("wrapper"))},[ae(s.$slots,"placeholder",{},()=>[g("div",{class:k(a(b).e("placeholder"))},null,2)])],2)):O("v-if",!0)],64)),a(F)?(E(),N(re,{key:2},[h.value?(E(),fe(a(zt),{key:0,"z-index":s.zIndex,"initial-index":a(X),infinite:s.infinite,"zoom-rate":s.zoomRate,"url-list":s.previewSrcList,"hide-on-click-modal":s.hideOnClickModal,teleported:s.previewTeleported,"close-on-press-escape":s.closeOnPressEscape,onClose:te,onSwitch:ne},{default:S(()=>[s.$slots.viewer?(E(),N("div",Ot,[ae(s.$slots,"viewer")])):O("v-if",!0)]),_:3},8,["z-index","initial-index","infinite","zoom-rate","url-list","hide-on-click-modal","teleported","close-on-press-escape"])):O("v-if",!0)],64)):O("v-if",!0)],6))}});var At=_e(Rt,[["__file","/home/runner/work/element-plus/element-plus/packages/components/image/src/image.vue"]]);const Bt=ke(At);const Vt="/assets/test-61b4d3e3.jpg";const Mt=e=>(nt("data-v-1e819e0f"),e=e(),at(),e),Wt={style:{whdth:"100vw",height:"100vh",overflow:"hidden",display:"flex"}},Dt={style:{flex:"1","background-color":"#6cf"}},Ft=Mt(()=>g("div",{class:"welcome-title"},[g("div",{style:{"font-size":"30px","font-weight":"bold"}},"欢迎来到围棋平台"),g("div",{style:{"margin-top":"20px"}})],-1)),Xt={style:{width:"400px","background-color":"white"}},Yt={__name:"WelcomeView",setup(e){return(r,t)=>{const o=Bt,c=tt("router-view");return E(),N("div",Wt,[g("div",Dt,[v(o,{style:{width:"100%",height:"100%"},fit:"cover",src:a(Vt)},null,8,["src"])]),Ft,g("div",Xt,[v(c)])])}}},Kt=et(Yt,[["__scopeId","data-v-1e819e0f"]]);export{Kt as default};
