import{an as L,E as j}from"./base-0a4413d9.js";import{b as J,c as Z,a as ee}from"./el-text-7a980412.js";import{p as K,j as U,a as te,c as se,b as ne}from"./index-ff51c493.js";/* empty css                */import{_ as x,o as g,c as p,K as I,a2 as D,a as f,Y as C,n as Q,j as k,k as V,r as W,b as n,d as z,q as ae,a7 as y,w as a,t as A,I as _,J as b}from"./index-8f4ee042.js";import{E as le}from"./index-f3287924.js";import"./scroll-0d4663fb.js";const ie={props:{dir:{type:String,required:!0,validator:e=>e==="x"||e==="y"},sets:{type:Array,required:!0},labels:{type:Array,required:!0}},computed:{_labels(){const{sets:e=[],labels:t=[" "]}=this,s=t[t.length-1];return e.map(i=>t[i]!==void 0?t[i]:s)}}},oe=["textContent"];function re(e,t,s,i,m,o){return g(),p("div",{class:Q(`shudan-coord${s.dir}`)},[(g(!0),p(I,null,D(o._labels,(u,d)=>(g(),p("div",{key:d},[f("span",{textContent:C(u)},null,8,oe)]))),128))],2)}const X=x(ie,[["render",re],["__scopeId","data-v-9f47c98d"]]),de="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAE3klEQVR4nO3czXFTVxjH4XMViPFkQwUgNhnYmQpCB5AKMBXYHcRUELsCnAridEAqwDuYbBBUwCZjHIhujpxhE4b/xPdDktHzzGjmyEtJv7nnPZJvU4AvEggEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCCQFXk5vTFtSvuwzMu0NGWn/ulzbTktkzJrS/Pbvdn7WWHpmvpgSS6imLd79VV/VNp2Wi6jaWalLSftpDkSy/IIZAn+mH63084/7LWl7JYB1DftuJlcP/p+9udpfcqI6mvNWF5Pb948n5/9VEq7X5+OoDncmmw/vTN7964+YQQCGcniqjFvP/566a3UZdWt16S59qOryTgEMoI6a+w28/mzulyadjJ5UmeT48KgBDKwVcTxiUiGJ5ABrTKOT0QyLIEM5GLmmH94UZcrN5lcv28mGYZABnBxWtWevRh9IP+/6uC+1Wzfd7rVn0AG8OrW1mHd3OzV5Rppju6+Pd+vC3oQSE/rtLX6rzqP3KnzyKzQmUB6qlePk/pRfFiXa6ct5Zd7b//aLXQmkB7qqdW0nlq9rsu15SrSj0B6qFePw/oR3KvLNWYW6UMgPby6vfV6bU6uvqSeaN19c36nruigqQ86uArbq09ss7oTSEevplv7Zd7+XJdrrwbypAZyXLg0gXR0NeaPf7WlPK2nWQeFSxNIRy9vbz1v2vaHulx7bdP8fu/N+YPCpQmkI4FsBoF0JJDNIJCOBLIZBNKRQDaDQDoSyGYQSEeOeTeDQDryReFmEEhHfmqyGQTSQ51DZnUOuV2Xa6vOH2/q/DEtdCKQHq7GHOLn7n0IpIersM2yvepHID29vPXtcX0RH9fl2mn9y21v9b2lDzdt+LoJZADrOYuYPYYgkAEsbhz3vj07XZcTrcXJ1Y1me8eN4/oTyEDWaavl1qPDEciA6qnWbj3VelaXK1Pnjid17jguDEIgA1tlJOIYnkBGsIpIxDEOgYxkMZP83X48GXtwXwzk3zTXHpk5xiGQES1Ot87nZwf1Y7xXRtEcbU22D5xWjUcgS3BxNZl/2K8v9uP6tLe2fkNej6oO6pZqVhhVfc9YljqbTJt5u9825dFlt16LrVTTlpN20hwKY3kEsiKLWEopD2owOzWYnbr+TA3itAZxWpfPRbEaAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCgUAgEAgEAoFAIBAIBAKBQCAQCAQCwT99z33YNNbIlQAAAABJRU5ErkJggg==";const ue={data:function(){return{point:de}},props:{sign:{type:Number,required:!0,default:0},selected:{type:Boolean,required:!0,default:!1},dimmed:{type:Boolean,required:!0,default:!1},animate:{type:Boolean,required:!0,default:!1},shift:{type:Number,required:!0,default:0},random:{type:Number,required:!0,default:0},paint:{type:Number,default:void 0},heat:{type:Object,default:void 0},marker:{type:Object,default:void 0},ghostStone:{type:Object,default:void 0}},computed:{classes(){const{sign:e,random:t,shift:s,heat:i,paint:m,dimmed:o,selected:u,animate:d,marker:c,ghostStone:r}=this;return["shudan-vertex",`shudan-sign_${e}`,`shudan-random_${t}`,{[`shudan-shift_${s}`]:!!s,[`shudan-heat_${i&&i.strength}`]:!!i&&!!i.strength,[`shudan-paint_${m}`]:!!m,"shudan-dimmed":o,"shudan-selected":u,"shudan-animate":d,[`shudan-marker_${c&&c.type}`]:!!c&&!!c.type,"shudan-smalllabel":!!c&&c.type==="label"&&!!c.label&&(c.label.includes(`
`)||c.label.length>=3),[`shudan-ghost_${r&&r.sign}`]:!!r&&!!r.sign,[`shudan-ghost_${r&&r.type}`]:!!r&&!!r.type,"shudan-ghost_faint":!!r&&!!r.faint}]}}},ce=["title"],he={key:"ghost",class:"shudan-ghost",style:{"z-index":"1"}},me={key:"stone",class:"shudan-stone",style:{"z-index":"2"}},fe={key:"shadow",class:"shudan-shadow"},ge=["title"],pe={key:"paint",class:"shudan-paint",style:{"z-index":"3"}},ye={key:"selection",class:"shudan-selection",style:{"z-index":"4"}},Ae=["src"],_e={key:"heat",class:"shudan-heat",style:{"z-index":"5"}},be=["textContent"];function Ce(e,t,s,i,m,o){return g(),p("div",{class:Q(o.classes),style:{position:"relative"},onClick:t[0]||(t[0]=u=>e.$emit("click")),onMousedown:t[1]||(t[1]=u=>e.$emit("mousedown")),onMouseup:t[2]||(t[2]=u=>e.$emit("mouseup")),onMousemove:t[3]||(t[3]=u=>e.$emit("mousemove")),onMouseenter:t[4]||(t[4]=u=>e.$emit("mouseenter")),onMouseleave:t[5]||(t[5]=u=>e.$emit("mouseleave"))},[!s.sign&&s.marker?(g(),p("div",{key:"marker",class:"shudan-marker",style:{"z-index":"0"},title:s.marker.label},null,8,ce)):k("",!0),!s.sign&&s.ghostStone?(g(),p("div",he)):k("",!0),f("div",me,[s.sign?(g(),p("div",fe)):k("",!0),s.sign?(g(),p("div",{key:"inner",class:Q(["shudan-inner","shudan-stone-image",`shudan-random_${s.random}`,`shudan-sign_${s.sign}`])},null,2)):k("",!0),s.sign&&s.marker?(g(),p("div",{key:"marker",class:"shudan-marker",title:s.marker.label},null,8,ge)):k("",!0)]),s.paint?(g(),p("div",pe)):k("",!0),s.selected?(g(),p("div",ye,[f("img",{src:e.point,style:{width:"100%",height:"100%"}},null,8,Ae)])):k("",!0),s.heat?(g(),p("div",_e)):k("",!0),s.heat?(g(),p("div",{key:"heatlabel",class:"shudan-heatlabel",style:{"z-index":"6"},textContent:C(s.heat.text&&s.heat.text.toString())},null,8,be)):k("",!0)],34)}const Y=x(ue,[["render",Ce],["__scopeId","data-v-d6a72804"]]);const P=.042,q=e=>(2*e+1)*.5,we={props:{width:{type:Number,required:!0},height:{type:Number,required:!0},xs:{type:Array,required:!0},ys:{type:Array,required:!0},hoshis:{type:Array,required:!0}},computed:{lines(){const{width:e,height:t,xs:s,ys:i}=this,[m,o]=[s[0],i[0]].map(d=>d===0?.5:0),u=(d,c,r)=>c[c.length-1]===r-1?(2*c.length-1)*.5-d:c.length-d;return[...i.map((d,c)=>({classes:"shudan-gridline shudan-horizontal",x:`${m}em`,y:`${q(c)}em`,width:`${u(m,s,e)}em`,height:`${P}em`})),...s.map((d,c)=>({classes:"shudan-gridline shudan-vertical",x:`${q(c)}em`,y:`${o}em`,width:`${P}em`,height:`${u(o,i,t)}em`}))]},points(){const{xs:e,ys:t,hoshis:s=[]}=this,i=[];return s.forEach(([m,o])=>{const[u,d]=[e.indexOf(m),t.indexOf(o)];u>=0&&d>=0&&i.push({x:`${q(u)+.5*P}em`,y:`${q(d)+.5*P}em`})}),i}}},ke={class:"shudan-grid"},ve=["x","y","width","height"],Be=["cx","cy"];function Se(e,t,s,i,m,o){return g(),p("svg",ke,[(g(!0),p(I,null,D(o.lines,(u,d)=>(g(),p("rect",{key:`l${d}`,class:Q(u.classes),x:u.x,y:u.y,width:u.width,height:u.height},null,10,ve))),128)),(g(!0),p(I,null,D(o.points,(u,d)=>(g(),p("circle",{key:`p${d}`,class:"shudan-hoshi",cx:u.x,cy:u.y,r:".1em"},null,8,Be))),128))])}const H=x(we,[["render",Se],["__scopeId","data-v-6911e563"]]),Ee="ABCDEFGHJKLMNOPQRSTUVWXYZ",ze=["Click","MouseDown","MouseUp","MouseMove","MouseEnter","MouseLeave"];function Ie(e){return[...Array(e)].map((t,s)=>s)}function Qe(e){return Math.floor(Math.random()*(e+1))}function xe([e,t]){return[[e,t],[e-1,t],[e+1,t],[e,t-1],[e,t+1]]}function N([e,t],[s,i]){return e===s&&t===i}function Me([e,t],[s,i]){return N(e,s)&&N(t,i)}function $e(e,t){if(Math.min(e,t)<6)return[];let[s,i]=[e,t].map(r=>r>=13?3:2),[m,o]=[e-s-1,t-i-1],[u,d]=[e,t].map(r=>(r-1)/2),c=[[s,o],[m,i],[m,o],[s,i]];return e%2!==0&&t%2!==0&&c.push([u,d]),e%2!==0&&c.push([u,i],[u,o]),t%2!==0&&c.push([s,d],[m,d]),c}function Ve(e,t){if(e===t||e.length===0||e.length!==t.length)return[];const s=[];for(let i=0;i<e.length;++i)e[i]===0&&t[i]!=null&&t[i]!==0&&s.push(i);return s}let S={alpha:Ee,vertexEvents:ze,range:Ie,random:Qe,neighborhood:xe,vertexEquals:N,lineEquals:Me,getHoshis:$e,diffSignMap:Ve};const De={props:{type:{type:String,required:!0,default:"line"},v1:{type:Array,required:!0,default:()=>[0,0]},v2:{type:Array,required:!0,default:()=>[0,0]}},computed:{styles(){const{v1:e,v2:t}=this;if(N(e,t))return;const[s,i]=e.map((c,r)=>t[r]-c),[m,o]=e.map((c,r)=>(c+t[r]+1)/2),u=Math.atan2(i,s)*180/Math.PI,d=Math.sqrt(s*s+i*i);return{position:"absolute",left:`${m}em`,top:`${o}em`,margin:0,width:`${d}em`,transform:`translateX(${-d/2}em) rotate(${u}deg)`}}}};function Pe(e,t,s,i,m,o){return g(),p("div",{class:Q(`shudan-${s.type}`),style:V(o.styles)},null,6)}const qe=x(De,[["render",Pe]]);const F=24,O=function(e,t,s){const i=e[s];if(!i)return;const m=[[[1,5,8],s-1,[3,7,6]],[[2,5,6],s-t,[4,7,8]],[[3,7,6],s+1,[1,5,8]],[[4,7,8],s+t,[2,5,6]]];for(let[o,u,d]of m){const c=e[u];c&&o.includes(i)&&d.includes(c)&&e.splice(u,1,0)}},Ne={components:{Coord:X,Grid:H,Vertex:Y,ULine:qe},props:{boardSize:{type:Number,default:19},maxWidth:{type:Number,default:480},maxHeight:{type:Number,default:480},animate:{type:Boolean,default:!1},busy:{type:Boolean,default:!1},rangeX:{type:Array,default:()=>[0,1/0]},rangeY:{type:Array,default:()=>[0,1/0]},signMap:{type:Array,default:()=>[]},coordX:{type:Array,default:()=>[...S.alpha]},coordY:{type:Array,default(){return[...Array(19)].map((e,t)=>19-t)}},showCoordinates:{type:Boolean,default:!1},fuzzyStonePlacement:{type:Boolean,default:!1},animateStonePlacement:{type:Boolean,default:!1},paintMap:{type:Array,default:void 0},heatMap:{type:Array,default:void 0},markerMap:{type:Array,default:void 0},ghostStoneMap:{type:Array,default:void 0},lines:{type:Array,default:void 0},dimmedMap:{type:Array,default:void 0},selectedMap:{type:Array,default:void 0}},data:function(){return{animatedVertices:[],clearAnimatedVerticesHandler:null,offsetWidth:void 0,offsetHeight:void 0,fontSize:void 0,updatingSize:!1}},computed:{vertexSize(){const{offsetWidth:e,offsetHeight:t,fontSize:s}=this;if(!e||!t||!s)return this.updateElementSize(),F;const{maxWidth:i,maxHeight:m}=this,o=Math.min(i/e,m/t);return Math.max(Math.floor(s*o),1)},shiftMap(){const e=this.boardSize,t=[...Array(e*e)].map(()=>S.random(8));return t.forEach((s,i)=>O(t,e,i)),t},randomMap(){const e=this.boardSize;return[...Array(e*e)].map(()=>S.random(4))},xs(){this.updateElementSize();const{boardSize:e,rangeX:t}=this;return S.range(e).slice(t[0],t[1]+1)},ys(){this.updateElementSize();const{boardSize:e,rangeY:t}=this;return S.range(e).slice(t[0],t[1]+1)},hoshis(){const e=this.boardSize;return S.getHoshis(e,e)},vertices(){const{xs:e,ys:t,fuzzyStonePlacement:s,shiftMap:i,randomMap:m,signMap:o,heatMap:u,paintMap:d,markerMap:c,ghostStoneMap:r,dimmedMap:h,selectedMap:w,animatedVertices:M,boardSize:v}=this,$=[];return t.forEach(E=>{e.forEach(R=>{const B=E*v+R;$.push({offset:B,shift:s?i&&i[B]:0,random:m&&m[B],sign:o&&o[B],heat:u&&u[B],paint:d&&d[B],marker:c&&c[B],ghostStone:r&&r[B],dimmed:h&&h[B],selected:w&&w[B],animate:M&&M.some(G=>G===B)})})}),$}},watch:{boardSize:{handler(){this.animatedVertices=[],this.clearAnimatedVerticesHandler=null},immediate:!0},signMap(e,t){const{animateStonePlacement:s,fuzzyStonePlacement:i,clearAnimatedVerticesHandler:m}=this;if(s&&i&&m==null){const o=S.diffSignMap(t,e);o.length>0&&(this.animatedVertices=o,this.updateAnimatedVertices())}},showCoordinates(){this.updateElementSize()}},methods:{updateAnimatedVertices(){if(this.animatedVertices.length>0){for(let e of this.animatedVertices)this.$set(this.shiftMap,e,S.random(7)+1),O(this.shiftMap,this.boardSize,e);this.$nextTick(function(){this.clearAnimatedVerticesHandler=window.setTimeout(()=>{this.animatedVertices=[],this.clearAnimatedVerticesHandler=null},200)})}},updateElementSize(){this.$nextTick(()=>{const e=this.$refs["goban-root"];e?(this.offsetWidth=e.offsetWidth,this.offsetHeight=e.offsetHeight,this.fontSize=parseInt(e.style.fontSize)||F):this.updateElementSize()})}}},Ue={class:"shudan-lines"};function Ke(e,t,s,i,m,o){const u=H,d=Y,c=W("ULine"),r=X;return g(),p("div",null,[f("div",{ref:"goban-root",class:Q(["shudan-goban shudan-goban-image",{"shudan-busy":s.busy,"shudan-coordinates":s.showCoordinates}]),style:V(`font-size: ${o.vertexSize}px;`)},[f("div",{class:"center shudan-content",style:V({position:"relative",width:`${o.xs.length}em`,height:`${o.ys.length}em`})},[n(u,{width:s.boardSize,height:s.boardSize,xs:o.xs,ys:o.ys,hoshis:o.hoshis},null,8,["width","height","xs","ys","hoshis"]),f("div",{class:"shudan-vertices",style:V({display:"grid",gridTemplateColumns:`repeat(${o.xs.length}, 1em)`,gridTemplateRows:`repeat(${o.ys.length}, 1em)`})},[(g(!0),p(I,null,D(o.vertices,h=>(g(),z(d,{key:h.offset,shift:h.shift,random:h.random,sign:h.sign,heat:h.heat,paint:h.paint,marker:h.marker,"ghost-stone":h.ghostStone,dimmed:h.dimmed,selected:h.selected,animate:h.animate,onClick:w=>e.$emit("click",h.offset),onMousedown:w=>e.$emit("mousedown",h.offset),onMouseup:w=>e.$emit("mouseup",h.offset),onMousemove:w=>e.$emit("mousemove",h.offset),onMouseenter:w=>e.$emit("mouseenter",h.offset),onMouseleave:w=>e.$emit("mouseleave",h.offset)},null,8,["shift","random","sign","heat","paint","marker","ghost-stone","dimmed","selected","animate","onClick","onMousedown","onMouseup","onMousemove","onMouseenter","onMouseleave"]))),128))],4),f("div",Ue,[f("div",{style:V({position:"absolute",top:`-${s.rangeY[0]}em`,left:`-${s.rangeX[0]}em`,width:`${s.boardSize}em`,height:`${s.boardSize}em`})},[(g(!0),p(I,null,D(s.lines,(h,w)=>(g(),z(c,{key:w,v1:h.v1,v2:h.v2,type:h.type},null,8,["v1","v2","type"]))),128))],4)])],4),s.showCoordinates?(g(),z(r,{key:0,class:"top",dir:"x",sets:o.xs,labels:s.coordX},null,8,["sets","labels"])):k("",!0),s.showCoordinates?(g(),z(r,{key:1,class:"left",dir:"y",sets:o.ys,labels:s.coordY},null,8,["sets","labels"])):k("",!0),s.showCoordinates?(g(),z(r,{key:2,class:"right",dir:"y",sets:o.ys,labels:s.coordY},null,8,["sets","labels"])):k("",!0),s.showCoordinates?(g(),z(r,{key:3,class:"bottom",dir:"x",sets:o.xs,labels:s.coordX},null,8,["sets","labels"])):k("",!0)],6)])}const T=x(Ne,[["render",Ke],["__scopeId","data-v-0152964d"]]),Fe="/assets/stone_1-8501003b.png",Oe="/assets/stone_-1-c71eee3e.png";const l=ae(),We={name:"Game",components:{Goban:T,CaretLeft:L},data:function(){return{signMap:y(l).jsonchessboard,maxSize:480,isBusy:!1,blackStone:Fe,whiteStone:Oe,playerIsBlack:y(l).playerisblack,roomId:y(l).roomid,userName:y(l).username,isOwner:y(l).isowner,blackPlayer:y(l).blackplayer,whitePlayer:y(l).whiteplayer,Winner:y(l).winner,whiteCount:y(l).whitecount,blackCount:y(l).blackcount,selectedMap:y(l).selectedmap,showDialog:y(l).showdialog,showDialogEnd:y(l).showdialogend,isNotSurrender:y(l).isnotsurrender,disableStartGame:y(l).disablestartgame,waitForResult:y(l).waitforresult}},methods:{onVertexClick:function(e){if(l.gamestart&&l.isdrop&&l.chessboard[e]===0){l.isdrop=!1;let t=parseInt(e/19),s=parseInt(e-19*t);K("/api/chessBoard/drops/"+l.userid+"/"+l.roomid,{dropPosition:[t,s]},i=>{},(i,m)=>{m===12003&&(l.isdrop=!0)})}},exitRoom:function(){U("/api/room/exit/"+l.userid+"/"+l.roomid,()=>{le({title:"返回成功",type:"success"})}),l.blackplayer.id="",l.blackplayer.name="",l.whiteplayer.id="",l.whiteplayer.name="",l.showdialog=!0,l.showdialogend=!1,l.gamestart=!1,l.disablestartgame=!0,this.$router.push("/homepage")},changePlayer:function(){let e=l.blackplayer;l.blackplayer=l.whiteplayer,l.whiteplayer=e},createGame:function(){K("/api/chessBoard/"+l.userid+"/"+l.roomid,{whitePlayerId:l.whiteplayer.id,blackPlayerId:l.blackplayer.id,boardSize:19,timeToDrop:60},()=>{}),l.gamestart=!1,this.showDialog=!1},resetData:function(){this.blackPlayer=y(l).blackplayer,this.whitePlayer=y(l).whiteplayer},waitOneStep:function(){l.gamestart&&l.isdrop&&(l.isdrop=!1,l.playerisblack=!l.playerisblack,U("/api/chessBoard/stop_once/"+l.userid+"/"+l.roomid,()=>{}))},surrender:function(){l.gamestart&&(l.winner="你选择投降",l.gamestart=!1,l.showdialogend=!0,l.isnotsurrender=!1,l.selectedmap=Array(19*19).fill(!1),U("/api/chessBoard/over_request/"+l.userid+"/"+l.roomid,()=>{}))}},computed:{checkBoxs:function(){return[{stateKey:"showCoordinates",text:"Show coordinates"},{stateKey:"isBusy",text:"Busy"}]}},watch:{checkedNames:function(){let{checkBoxs:e,checkedNames:t}=this;e.map(s=>{let{stateKey:i}=s,m=t.indexOf(i)>-1;this[i]!=m&&(this[i]=m)})}},mounted(){this.maxSize=Math.min(this.$refs.box.offsetWidth,this.$refs.box.offsetHeight)},destroyed(){}},Xe={style:{width:"100vw",height:"100vh",overflow:"hidden",display:"flex","background-color":"antiquewhite"},ref:"box"},Ye={style:{flex:"1"}},He={style:{width:"450px","margin-right":"15px"}},Te=["src"],Re=["src"],Ge={style:{"text-align":"center"}},Le=["src"],je={style:{"font-size":"large"}},Je=["src"],Ze={style:{"font-size":"large"}},et={slot:"footer",class:"dialog-footer",style:{"text-align":"right"}},tt={style:{"text-align":"center"}},st={style:{"text-align":"center"}},nt=["src"],at={style:{"font-size":"large"}},lt={style:{"font-size":"large"}},it=["src"],ot={style:{"font-size":"large"}},rt={style:{"font-size":"large"}},dt={slot:"footer",class:"dialog-footer",style:{"text-align":"right","margin-top":"20px"}};function ut(e,t,s,i,m,o){const u=T,d=Z,c=ee,r=se,h=te,w=W("CaretLeft"),M=j,v=ne,$=J;return g(),p(I,null,[f("div",Xe,[f("div",Ye,[n(u,{"max-width":e.maxSize,"max-height":e.maxSize,"sign-map":e.signMap,"selected-map":e.selectedMap,onClick:o.onVertexClick,style:{"text-align":"center"}},null,8,["max-width","max-height","sign-map","selected-map","onClick"])]),f("div",He,[n(h,{style:{"margin-top":"30px"}},{default:a(()=>[n(r,null,{default:a(()=>[n(c,{class:"card"},{default:a(()=>[n(d,{style:{"font-size":"20px"}},{default:a(()=>[A(" 房间ID： "+C(e.roomId),1)]),_:1})]),_:1})]),_:1})]),_:1}),n(h,{style:{"margin-top":"30px"}},{default:a(()=>[n(r,null,{default:a(()=>[n(c,{class:"card"},{default:a(()=>[n(d,{style:{"font-size":"20px"}},{default:a(()=>[A(" 用户名： "+C(e.userName),1)]),_:1})]),_:1})]),_:1})]),_:1}),n(h,{gutter:20,style:{"margin-top":"20px"}},{default:a(()=>[n(r,{span:12},{default:a(()=>[n(c,{class:"card"},{default:a(()=>[n(d,{style:{"font-size":"20px"}},{default:a(()=>[f("img",{src:e.blackStone,class:"stone"},null,8,Te),f("span",null,C(" "+e.blackPlayer.name),1),_(n(M,null,{default:a(()=>[n(w)]),_:1},512),[[b,e.playerIsBlack]])]),_:1})]),_:1})]),_:1}),n(r,{span:12},{default:a(()=>[n(c,{class:"card"},{default:a(()=>[n(d,{style:{"font-size":"20px"}},{default:a(()=>[f("img",{src:e.whiteStone,class:"stone"},null,8,Re),f("span",null,C(" "+e.whitePlayer.name),1),_(n(M,null,{default:a(()=>[n(w)]),_:1},512),[[b,!e.playerIsBlack]])]),_:1})]),_:1})]),_:1})]),_:1}),n(h,{style:{"margin-top":"20px"}},{default:a(()=>[n(r,null,{default:a(()=>[n(c,{class:"card"},{default:a(()=>[n(h,{style:{"margin-top":"10px"}},{default:a(()=>[n(r,{span:6},{default:a(()=>[n(v,{type:"success",onClick:o.waitOneStep},{default:a(()=>[A("停一手")]),_:1},8,["onClick"])]),_:1}),n(r,{span:6},{default:a(()=>[n(v,{type:"warning",onClick:o.surrender},{default:a(()=>[A("认输")]),_:1},8,["onClick"])]),_:1})]),_:1}),n(h,{style:{"margin-top":"10px"}},{default:a(()=>[n(r,{span:6},{default:a(()=>[n(v,{type:"danger",onClick:o.exitRoom},{default:a(()=>[A("退出房间")]),_:1},8,["onClick"])]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})])],512),n($,{title:"开始游戏",modelValue:e.showDialog,"onUpdate:modelValue":t[0]||(t[0]=E=>e.showDialog=E),width:"30%","close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1,"before-close":o.resetData()},{default:a(()=>[f("div",Ge,[_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,{style:{"font-size":"large"}},{default:a(()=>[A("房间ID： "+C(e.roomId),1)]),_:1})]),_:1})]),_:1},512),[[b,e.isOwner]]),_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,null,{default:a(()=>[f("img",{src:e.blackStone,class:"stone"},null,8,Le),f("span",je,C(" "+e.blackPlayer.name),1)]),_:1})]),_:1})]),_:1},512),[[b,e.isOwner]]),_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,null,{default:a(()=>[f("img",{src:e.whiteStone,class:"stone"},null,8,Je),f("span",Ze,C(" "+e.whitePlayer.name),1)]),_:1})]),_:1})]),_:1},512),[[b,e.isOwner]]),_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(v,{type:"warning",onClick:o.changePlayer},{default:a(()=>[A("切换黑白方")]),_:1},8,["onClick"])]),_:1})]),_:1},512),[[b,e.isOwner]]),_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,null,{default:a(()=>[A(" 等待房主开始游戏 ")]),_:1})]),_:1})]),_:1},512),[[b,!e.isOwner]]),_(n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(v,{type:"danger",onClick:o.exitRoom},{default:a(()=>[A("退出房间")]),_:1},8,["onClick"])]),_:1})]),_:1},512),[[b,!e.isOwner]])]),_(f("div",et,[n(v,{onClick:o.exitRoom},{default:a(()=>[A("退出房间")]),_:1},8,["onClick"]),n(v,{type:"primary",onClick:o.createGame,disabled:e.disableStartGame},{default:a(()=>[A("开始游戏")]),_:1},8,["onClick","disabled"])],512),[[b,e.isOwner]])]),_:1},8,["modelValue","before-close"]),n($,{title:"游戏结束",modelValue:e.showDialogEnd,"onUpdate:modelValue":t[3]||(t[3]=E=>e.showDialogEnd=E),width:"30%","close-on-click-modal":!1,"close-on-press-escape":!1,"show-close":!1,"before-close":o.resetData()},{default:a(()=>[_(f("div",null,[n(h,null,{default:a(()=>[n(r,{style:{"text-align":"center"}},{default:a(()=>[n(d,{style:{"font-size":"large"}},{default:a(()=>[A(C(e.Winner),1)]),_:1})]),_:1})]),_:1})],512),[[b,!e.isNotSurrender]]),_(f("div",null,[_(f("div",tt,[n(d,{size:"large"},{default:a(()=>[A(" 等待结果中…… ")]),_:1})],512),[[b,e.waitForResult]]),_(f("div",st,[f("div",null,[n(h,null,{default:a(()=>[n(r,{style:{"text-align":"center"}},{default:a(()=>[n(d,{style:{"font-size":"large"}},{default:a(()=>[A(C(e.Winner),1)]),_:1})]),_:1})]),_:1}),n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,null,{default:a(()=>[f("img",{src:e.blackStone,class:"stone"},null,8,nt),f("span",at,C(" "+e.blackPlayer.name)+"：",1),f("span",lt,C(" "+e.blackCount+"目"),1)]),_:1})]),_:1})]),_:1}),n(h,null,{default:a(()=>[n(r,null,{default:a(()=>[n(d,null,{default:a(()=>[f("img",{src:e.whiteStone,class:"stone"},null,8,it),f("span",ot,C(" "+e.whitePlayer.name)+"：",1),f("span",rt,C(" "+e.whiteCount+"目"),1)]),_:1})]),_:1})]),_:1})])],512),[[b,!e.waitForResult]])],512),[[b,e.isNotSurrender]]),_(f("div",dt,[_(n(v,{type:"success",onClick:t[1]||(t[1]=E=>{e.showDialogEnd=!1,e.showDialog=!0})},{default:a(()=>[A("开始新游戏")]),_:1},512),[[b,e.isOwner]]),_(n(v,{type:"success",onClick:t[2]||(t[2]=E=>{e.showDialogEnd=!1,e.showDialog=!0})},{default:a(()=>[A("等待下一局")]),_:1},512),[[b,!e.isOwner]]),n(v,{type:"danger",onClick:o.exitRoom},{default:a(()=>[A("退出房间")]),_:1},8,["onClick"])],512),[[b,!e.waitForResult]])]),_:1},8,["modelValue","before-close"])],64)}const At=x(We,[["render",ut]]);export{At as default};