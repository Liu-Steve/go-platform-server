import{aN as Fe,t as j,b8 as je,aO as te,aU as re,b9 as Ge,b2 as q,aq as We,au as Xe,j as ve,i as ee,ba as he,w as H,bb as qe,bc as Je,bd as Ze,be as Qe,Y as Me,l as L,_ as me,f as V,ax as Ne,C as l,U as D,q as ie,a3 as et,c as pe,d as ce,y as x,bf as tt,aJ as ot,u as nt,an as be,p as U,v as K,I as se,A as J,B as M,a4 as st,z as $,x as ue,O as at,G as lt,F as de,D as Be,bg as rt,am as it,k as ct,bh as ut,ap as dt,bi as ft,bj as ge,at as vt,S as mt,a1 as pt,bk as Et,T as yt,V as ht,W as bt,$ as gt}from"./index-49f4e068.js";import{t as Tt,l as Ct,U as Pe,k as Te,m as Ce}from"./index-ba1dc6a7.js";import{a as St}from"./scroll-fc8f63e7.js";const wt=(...e)=>t=>{e.forEach(o=>{Fe(o)?o(t):o.value=t})};var Z=(e=>(e[e.TEXT=1]="TEXT",e[e.CLASS=2]="CLASS",e[e.STYLE=4]="STYLE",e[e.PROPS=8]="PROPS",e[e.FULL_PROPS=16]="FULL_PROPS",e[e.HYDRATE_EVENTS=32]="HYDRATE_EVENTS",e[e.STABLE_FRAGMENT=64]="STABLE_FRAGMENT",e[e.KEYED_FRAGMENT=128]="KEYED_FRAGMENT",e[e.UNKEYED_FRAGMENT=256]="UNKEYED_FRAGMENT",e[e.NEED_PATCH=512]="NEED_PATCH",e[e.DYNAMIC_SLOTS=1024]="DYNAMIC_SLOTS",e[e.HOISTED=-1]="HOISTED",e[e.BAIL=-2]="BAIL",e))(Z||{});const kt=(e,t,o)=>{let n={offsetX:0,offsetY:0};const a=c=>{const v=c.clientX,T=c.clientY,{offsetX:m,offsetY:C}=n,h=e.value.getBoundingClientRect(),f=h.left,s=h.top,d=h.width,p=h.height,E=document.documentElement.clientWidth,y=document.documentElement.clientHeight,F=-f+m,I=-s+C,A=E-f-d+m,O=y-s-p+C,_=S=>{const u=Math.min(Math.max(m+S.clientX-v,F),A),w=Math.min(Math.max(C+S.clientY-T,I),O);n={offsetX:u,offsetY:w},e.value.style.transform=`translate(${re(u)}, ${re(w)})`},b=()=>{document.removeEventListener("mousemove",_),document.removeEventListener("mouseup",b)};document.addEventListener("mousemove",_),document.addEventListener("mouseup",b)},r=()=>{t.value&&e.value&&t.value.addEventListener("mousedown",a)},i=()=>{t.value&&e.value&&t.value.removeEventListener("mousedown",a)};j(()=>{je(()=>{o.value?r():i()})}),te(()=>{i()})};let _t;function Lt(e,t=_t){t&&t.active&&t.effects.push(e)}const At=e=>{const t=new Set(e);return t.w=0,t.n=0,t},$e=e=>(e.w&B)>0,Ue=e=>(e.n&B)>0,Rt=({deps:e})=>{if(e.length)for(let t=0;t<e.length;t++)e[t].w|=B},Dt=e=>{const{deps:t}=e;if(t.length){let o=0;for(let n=0;n<t.length;n++){const a=t[n];$e(a)&&!Ue(a)?a.delete(e):t[o++]=a,a.w&=~B,a.n&=~B}t.length=o}};let z=0,B=1;const fe=30;let R;class It{constructor(t,o=null,n){this.fn=t,this.scheduler=o,this.active=!0,this.deps=[],this.parent=void 0,Lt(this,n)}run(){if(!this.active)return this.fn();let t=R,o=Q;for(;t;){if(t===this)return;t=t.parent}try{return this.parent=R,R=this,Q=!0,B=1<<++z,z<=fe?Rt(this):Se(this),this.fn()}finally{z<=fe&&Dt(this),B=1<<--z,R=this.parent,Q=o,this.parent=void 0,this.deferStop&&this.stop()}}stop(){R===this?this.deferStop=!0:this.active&&(Se(this),this.onStop&&this.onStop(),this.active=!1)}}function Se(e){const{deps:t}=e;if(t.length){for(let o=0;o<t.length;o++)t[o].delete(e);t.length=0}}let Q=!0;function Ot(e,t){let o=!1;z<=fe?Ue(e)||(e.n|=B,o=!$e(e)):o=!e.has(R),o&&(e.add(R),R.deps.push(e))}function Ft(e,t){const o=We(e)?e:[...e];for(const n of o)n.computed&&we(n);for(const n of o)n.computed||we(n)}function we(e,t){(e!==R||e.allowRecurse)&&(e.scheduler?e.scheduler():e.run())}new Set(Object.getOwnPropertyNames(Symbol).filter(e=>e!=="arguments"&&e!=="caller").map(e=>Symbol[e]).filter(Ge));function oe(e){const t=e&&e.__v_raw;return t?oe(t):e}function Mt(e){Q&&R&&(e=oe(e),Ot(e.dep||(e.dep=At())))}function Nt(e,t){e=oe(e);const o=e.dep;o&&Ft(o)}class Bt{constructor(t,o,n,a){this._setter=o,this.dep=void 0,this.__v_isRef=!0,this.__v_isReadonly=!1,this._dirty=!0,this.effect=new It(t,()=>{this._dirty||(this._dirty=!0,Nt(this))}),this.effect.computed=this,this.effect.active=this._cacheable=!a,this.__v_isReadonly=n}get value(){const t=oe(this);return Mt(t),(t._dirty||!t._cacheable)&&(t._dirty=!1,t._value=t.effect.run()),t._value}set value(t){this._setter(t)}}function Pt(e,t,o=!1){let n,a;const r=Fe(e);return r?(n=e,a=q):(n=e.get,a=e.set),new Bt(n,a,r||!a,o)}const $t=(e,t={})=>{Xe(e)||Tt("[useLockscreen]","You need to pass a ref param to this function");const o=t.ns||ve("popup"),n=Pt(()=>o.bm("parent","hidden"));if(!ee||he(document.body,n.value))return;let a=0,r=!1,i="0";const c=()=>{setTimeout(()=>{Qe(document==null?void 0:document.body,n.value),r&&document&&(document.body.style.width=i)},200)};H(e,v=>{if(!v){c();return}r=!he(document.body,n.value),r&&(i=document.body.style.width),a=St(o.namespace.value);const T=document.documentElement.clientHeight<document.body.scrollHeight,m=qe(document.body,"overflowY");a>0&&(T||m==="scroll")&&r&&(document.body.style.width=`calc(100% - ${a}px)`),Je(document.body,n.value)}),Ze(()=>c())},Ye=e=>{if(!e)return{onClick:q,onMousedown:q,onMouseup:q};let t=!1,o=!1;return{onClick:i=>{t&&o&&e(i),t=o=!1},onMousedown:i=>{t=i.target===i.currentTarget},onMouseup:i=>{o=i.target===i.currentTarget}}};let Y=[];const ke=e=>{const t=e;t.key===Me.esc&&Y.forEach(o=>o(t))},Ut=e=>{j(()=>{Y.length===0&&document.addEventListener("keydown",ke),ee&&Y.push(e)}),te(()=>{Y=Y.filter(t=>t!==e),Y.length===0&&ee&&document.removeEventListener("keydown",ke)})},ae="focus-trap.focus-after-trapped",le="focus-trap.focus-after-released",Yt="focus-trap.focusout-prevented",_e={cancelable:!0,bubbles:!1},Kt={cancelable:!0,bubbles:!1},Le="focusAfterTrapped",Ae="focusAfterReleased",Ke=Symbol("elFocusTrap"),Ee=L(),ne=L(0),ye=L(0);let G=0;const He=e=>{const t=[],o=document.createTreeWalker(e,NodeFilter.SHOW_ELEMENT,{acceptNode:n=>{const a=n.tagName==="INPUT"&&n.type==="hidden";return n.disabled||n.hidden||a?NodeFilter.FILTER_SKIP:n.tabIndex>=0||n===document.activeElement?NodeFilter.FILTER_ACCEPT:NodeFilter.FILTER_SKIP}});for(;o.nextNode();)t.push(o.currentNode);return t},Re=(e,t)=>{for(const o of e)if(!Ht(o,t))return o},Ht=(e,t)=>{if(getComputedStyle(e).visibility==="hidden")return!0;for(;e;){if(t&&e===t)return!1;if(getComputedStyle(e).display==="none")return!0;e=e.parentElement}return!1},Vt=e=>{const t=He(e),o=Re(t,e),n=Re(t.reverse(),e);return[o,n]},zt=e=>e instanceof HTMLInputElement&&"select"in e,N=(e,t)=>{if(e&&e.focus){const o=document.activeElement;e.focus({preventScroll:!0}),ye.value=window.performance.now(),e!==o&&zt(e)&&t&&e.select()}};function De(e,t){const o=[...e],n=e.indexOf(t);return n!==-1&&o.splice(n,1),o}const xt=()=>{let e=[];return{push:n=>{const a=e[0];a&&n!==a&&a.pause(),e=De(e,n),e.unshift(n)},remove:n=>{var a,r;e=De(e,n),(r=(a=e[0])==null?void 0:a.resume)==null||r.call(a)}}},jt=(e,t=!1)=>{const o=document.activeElement;for(const n of e)if(N(n,t),document.activeElement!==o)return},Ie=xt(),Gt=()=>ne.value>ye.value,W=()=>{Ee.value="pointer",ne.value=window.performance.now()},Oe=()=>{Ee.value="keyboard",ne.value=window.performance.now()},Wt=()=>(j(()=>{G===0&&(document.addEventListener("mousedown",W),document.addEventListener("touchstart",W),document.addEventListener("keydown",Oe)),G++}),te(()=>{G--,G<=0&&(document.removeEventListener("mousedown",W),document.removeEventListener("touchstart",W),document.removeEventListener("keydown",Oe))}),{focusReason:Ee,lastUserFocusTimestamp:ne,lastAutomatedFocusTimestamp:ye}),X=e=>new CustomEvent(Yt,{...Kt,detail:e}),Xt=V({name:"ElFocusTrap",inheritAttrs:!1,props:{loop:Boolean,trapped:Boolean,focusTrapEl:Object,focusStartEl:{type:[Object,String],default:"first"}},emits:[Le,Ae,"focusin","focusout","focusout-prevented","release-requested"],setup(e,{emit:t}){const o=L();let n,a;const{focusReason:r}=Wt();Ut(s=>{e.trapped&&!i.paused&&t("release-requested",s)});const i={paused:!1,pause(){this.paused=!0},resume(){this.paused=!1}},c=s=>{if(!e.loop&&!e.trapped||i.paused)return;const{key:d,altKey:p,ctrlKey:E,metaKey:y,currentTarget:F,shiftKey:I}=s,{loop:A}=e,O=d===Me.tab&&!p&&!E&&!y,_=document.activeElement;if(O&&_){const b=F,[S,u]=Vt(b);if(S&&u){if(!I&&_===u){const g=X({focusReason:r.value});t("focusout-prevented",g),g.defaultPrevented||(s.preventDefault(),A&&N(S,!0))}else if(I&&[S,b].includes(_)){const g=X({focusReason:r.value});t("focusout-prevented",g),g.defaultPrevented||(s.preventDefault(),A&&N(u,!0))}}else if(_===b){const g=X({focusReason:r.value});t("focusout-prevented",g),g.defaultPrevented||s.preventDefault()}}};Ne(Ke,{focusTrapRef:o,onKeydown:c}),H(()=>e.focusTrapEl,s=>{s&&(o.value=s)},{immediate:!0}),H([o],([s],[d])=>{s&&(s.addEventListener("keydown",c),s.addEventListener("focusin",m),s.addEventListener("focusout",C)),d&&(d.removeEventListener("keydown",c),d.removeEventListener("focusin",m),d.removeEventListener("focusout",C))});const v=s=>{t(Le,s)},T=s=>t(Ae,s),m=s=>{const d=l(o);if(!d)return;const p=s.target,E=s.relatedTarget,y=p&&d.contains(p);e.trapped||E&&d.contains(E)||(n=E),y&&t("focusin",s),!i.paused&&e.trapped&&(y?a=p:N(a,!0))},C=s=>{const d=l(o);if(!(i.paused||!d))if(e.trapped){const p=s.relatedTarget;!Ct(p)&&!d.contains(p)&&setTimeout(()=>{if(!i.paused&&e.trapped){const E=X({focusReason:r.value});t("focusout-prevented",E),E.defaultPrevented||N(a,!0)}},0)}else{const p=s.target;p&&d.contains(p)||t("focusout",s)}};async function h(){await ie();const s=l(o);if(s){Ie.push(i);const d=s.contains(document.activeElement)?n:document.activeElement;if(n=d,!s.contains(d)){const E=new Event(ae,_e);s.addEventListener(ae,v),s.dispatchEvent(E),E.defaultPrevented||ie(()=>{let y=e.focusStartEl;et(y)||(N(y),document.activeElement!==y&&(y="first")),y==="first"&&jt(He(s),!0),(document.activeElement===d||y==="container")&&N(s)})}}}function f(){const s=l(o);if(s){s.removeEventListener(ae,v);const d=new CustomEvent(le,{..._e,detail:{focusReason:r.value}});s.addEventListener(le,T),s.dispatchEvent(d),!d.defaultPrevented&&(r.value=="keyboard"||!Gt()||s.contains(document.activeElement))&&N(n??document.body),s.removeEventListener(le,T),Ie.remove(i)}}return j(()=>{e.trapped&&h(),H(()=>e.trapped,s=>{s?h():f()})}),te(()=>{e.trapped&&f()}),{onKeydown:c}}});function qt(e,t,o,n,a,r){return D(e.$slots,"default",{handleKeydown:e.onKeydown})}var Jt=me(Xt,[["render",qt],["__file","/home/runner/work/element-plus/element-plus/packages/components/focus-trap/src/focus-trap.vue"]]);const Zt=pe({mask:{type:Boolean,default:!0},customMaskEvent:{type:Boolean,default:!1},overlayClass:{type:ce([String,Array,Object])},zIndex:{type:ce([String,Number])}}),Qt={click:e=>e instanceof MouseEvent},eo="overlay";var to=V({name:"ElOverlay",props:Zt,emits:Qt,setup(e,{slots:t,emit:o}){const n=ve(eo),a=v=>{o("click",v)},{onClick:r,onMousedown:i,onMouseup:c}=Ye(e.customMaskEvent?void 0:a);return()=>e.mask?x("div",{class:[n.b(),e.overlayClass],style:{zIndex:e.zIndex},onClick:r,onMousedown:i,onMouseup:c},[D(t,"default")],Z.STYLE|Z.CLASS|Z.PROPS,["onClick","onMouseup","onMousedown"]):tt("div",{class:e.overlayClass,style:{zIndex:e.zIndex,position:"fixed",top:"0px",right:"0px",bottom:"0px",left:"0px"}},[D(t,"default")])}});const oo=to,Ve=Symbol("dialogInjectionKey"),ze=pe({center:Boolean,alignCenter:Boolean,closeIcon:{type:ot},customClass:{type:String,default:""},draggable:Boolean,fullscreen:Boolean,showClose:{type:Boolean,default:!0},title:{type:String,default:""},ariaLevel:{type:String,default:"2"}}),no={close:()=>!0},so=["aria-level"],ao=["aria-label"],lo=["id"],ro=V({name:"ElDialogContent"}),io=V({...ro,props:ze,emits:no,setup(e){const t=e,{t:o}=nt(),{Close:n}=rt,{dialogRef:a,headerRef:r,bodyId:i,ns:c,style:v}=be(Ve),{focusTrapRef:T}=be(Ke),m=U(()=>[c.b(),c.is("fullscreen",t.fullscreen),c.is("draggable",t.draggable),c.is("align-center",t.alignCenter),{[c.m("center")]:t.center},t.customClass]),C=wt(T,a),h=U(()=>t.draggable);return kt(a,r,h),(f,s)=>(K(),se("div",{ref:l(C),class:M(l(m)),style:Be(l(v)),tabindex:"-1"},[J("header",{ref_key:"headerRef",ref:r,class:M(l(c).e("header"))},[D(f.$slots,"header",{},()=>[J("span",{role:"heading","aria-level":f.ariaLevel,class:M(l(c).e("title"))},st(f.title),11,so)]),f.showClose?(K(),se("button",{key:0,"aria-label":l(o)("el.dialog.close"),class:M(l(c).e("headerbtn")),type:"button",onClick:s[0]||(s[0]=d=>f.$emit("close"))},[x(l(lt),{class:M(l(c).e("close"))},{default:$(()=>[(K(),ue(at(f.closeIcon||l(n))))]),_:1},8,["class"])],10,ao)):de("v-if",!0)],2),J("div",{id:l(i),class:M(l(c).e("body"))},[D(f.$slots,"default")],10,lo),f.$slots.footer?(K(),se("footer",{key:0,class:M(l(c).e("footer"))},[D(f.$slots,"footer")],2)):de("v-if",!0)],6))}});var co=me(io,[["__file","/home/runner/work/element-plus/element-plus/packages/components/dialog/src/dialog-content.vue"]]);const uo=pe({...ze,appendToBody:Boolean,beforeClose:{type:ce(Function)},destroyOnClose:Boolean,closeOnClickModal:{type:Boolean,default:!0},closeOnPressEscape:{type:Boolean,default:!0},lockScroll:{type:Boolean,default:!0},modal:{type:Boolean,default:!0},openDelay:{type:Number,default:0},closeDelay:{type:Number,default:0},top:{type:String},modelValue:Boolean,modalClass:String,width:{type:[String,Number]},zIndex:{type:Number},trapFocus:{type:Boolean,default:!1},headerAriaLevel:{type:String,default:"2"}}),fo={open:()=>!0,opened:()=>!0,close:()=>!0,closed:()=>!0,[Pe]:e=>it(e),openAutoFocus:()=>!0,closeAutoFocus:()=>!0},vo=(e,t)=>{const n=dt().emit,{nextZIndex:a}=ct();let r="";const i=Te(),c=Te(),v=L(!1),T=L(!1),m=L(!1),C=L(e.zIndex||a());let h,f;const s=ut("namespace",ft),d=U(()=>{const k={},P=`--${s.value}-dialog`;return e.fullscreen||(e.top&&(k[`${P}-margin-top`]=e.top),e.width&&(k[`${P}-width`]=re(e.width))),k}),p=U(()=>e.alignCenter?{display:"flex"}:{});function E(){n("opened")}function y(){n("closed"),n(Pe,!1),e.destroyOnClose&&(m.value=!1)}function F(){n("close")}function I(){f==null||f(),h==null||h(),e.openDelay&&e.openDelay>0?{stop:h}=ge(()=>b(),e.openDelay):b()}function A(){h==null||h(),f==null||f(),e.closeDelay&&e.closeDelay>0?{stop:f}=ge(()=>S(),e.closeDelay):S()}function O(){function k(P){P||(T.value=!0,v.value=!1)}e.beforeClose?e.beforeClose(k):A()}function _(){e.closeOnClickModal&&O()}function b(){ee&&(v.value=!0)}function S(){v.value=!1}function u(){n("openAutoFocus")}function w(){n("closeAutoFocus")}function g(k){var P;((P=k.detail)==null?void 0:P.focusReason)==="pointer"&&k.preventDefault()}e.lockScroll&&$t(v);function xe(){e.closeOnPressEscape&&O()}return H(()=>e.modelValue,k=>{k?(T.value=!1,I(),m.value=!0,C.value=e.zIndex?C.value++:a(),ie(()=>{n("open"),t.value&&(t.value.scrollTop=0)})):v.value&&A()}),H(()=>e.fullscreen,k=>{t.value&&(k?(r=t.value.style.transform,t.value.style.transform=""):t.value.style.transform=r)}),j(()=>{e.modelValue&&(v.value=!0,m.value=!0,I())}),{afterEnter:E,afterLeave:y,beforeLeave:F,handleClose:O,onModalClick:_,close:A,doClose:S,onOpenAutoFocus:u,onCloseAutoFocus:w,onCloseRequested:xe,onFocusoutPrevented:g,titleId:i,bodyId:c,closed:T,style:d,overlayDialogStyle:p,rendered:m,visible:v,zIndex:C}},mo=["aria-label","aria-labelledby","aria-describedby"],po=V({name:"ElDialog",inheritAttrs:!1}),Eo=V({...po,props:uo,emits:fo,setup(e,{expose:t}){const o=e,n=vt();Ce({scope:"el-dialog",from:"the title slot",replacement:"the header slot",version:"3.0.0",ref:"https://element-plus.org/en-US/component/dialog.html#slots"},U(()=>!!n.title)),Ce({scope:"el-dialog",from:"custom-class",replacement:"class",version:"2.3.0",ref:"https://element-plus.org/en-US/component/dialog.html#attributes",type:"Attribute"},U(()=>!!o.customClass));const a=ve("dialog"),r=L(),i=L(),c=L(),{visible:v,titleId:T,bodyId:m,style:C,overlayDialogStyle:h,rendered:f,zIndex:s,afterEnter:d,afterLeave:p,beforeLeave:E,handleClose:y,onModalClick:F,onOpenAutoFocus:I,onCloseAutoFocus:A,onCloseRequested:O,onFocusoutPrevented:_}=vo(o,r);Ne(Ve,{dialogRef:r,headerRef:i,bodyId:m,ns:a,rendered:f,style:C});const b=Ye(F),S=U(()=>o.draggable&&!o.fullscreen);return t({visible:v,dialogContentRef:c}),(u,w)=>(K(),ue(bt,{to:"body",disabled:!u.appendToBody},[x(ht,{name:"dialog-fade",onAfterEnter:l(d),onAfterLeave:l(p),onBeforeLeave:l(E),persisted:""},{default:$(()=>[mt(x(l(oo),{"custom-mask-event":"",mask:u.modal,"overlay-class":u.modalClass,"z-index":l(s)},{default:$(()=>[J("div",{role:"dialog","aria-modal":"true","aria-label":u.title||void 0,"aria-labelledby":u.title?void 0:l(T),"aria-describedby":l(m),class:M(`${l(a).namespace.value}-overlay-dialog`),style:Be(l(h)),onClick:w[0]||(w[0]=(...g)=>l(b).onClick&&l(b).onClick(...g)),onMousedown:w[1]||(w[1]=(...g)=>l(b).onMousedown&&l(b).onMousedown(...g)),onMouseup:w[2]||(w[2]=(...g)=>l(b).onMouseup&&l(b).onMouseup(...g))},[x(l(Jt),{loop:"",trapped:l(v),"focus-start-el":"container",onFocusAfterTrapped:l(I),onFocusAfterReleased:l(A),onFocusoutPrevented:l(_),onReleaseRequested:l(O)},{default:$(()=>[l(f)?(K(),ue(co,pt({key:0,ref_key:"dialogContentRef",ref:c},u.$attrs,{"custom-class":u.customClass,center:u.center,"align-center":u.alignCenter,"close-icon":u.closeIcon,draggable:l(S),fullscreen:u.fullscreen,"show-close":u.showClose,title:u.title,"aria-level":u.headerAriaLevel,onClose:l(y)}),Et({header:$(()=>[u.$slots.title?D(u.$slots,"title",{key:1}):D(u.$slots,"header",{key:0,close:l(y),titleId:l(T),titleClass:l(a).e("title")})]),default:$(()=>[D(u.$slots,"default")]),_:2},[u.$slots.footer?{name:"footer",fn:$(()=>[D(u.$slots,"footer")])}:void 0]),1040,["custom-class","center","align-center","close-icon","draggable","fullscreen","show-close","title","aria-level","onClose"])):de("v-if",!0)]),_:3},8,["trapped","onFocusAfterTrapped","onFocusAfterReleased","onFocusoutPrevented","onReleaseRequested"])],46,mo)]),_:3},8,["mask","overlay-class","z-index"]),[[yt,l(v)]])]),_:3},8,["onAfterEnter","onAfterLeave","onBeforeLeave"])],8,["disabled"]))}});var yo=me(Eo,[["__file","/home/runner/work/element-plus/element-plus/packages/components/dialog/src/dialog.vue"]]);const To=gt(yo);export{To as E};
