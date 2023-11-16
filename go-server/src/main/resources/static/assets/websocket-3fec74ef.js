import{k as d}from"./index-dfb80fbc.js";import{M as b}from"./index-10d3ea48.js";const e=b();var n=null;function f(o){try{"WebSocket"in window&&(n=new WebSocket(o),h(n,o))}catch(l){m(o),console.log(l)}}function p(){n.close(),e.ws_state=!1,u.reset()}function h(o,l){o.onopen=function(c){u.reset().start(),console.log("WebSocket已连接")},o.onclose=function(c){m(l),console.log("WebSocket连接已关闭")},o.onerror=function(c){m(l),console.log("WebSocket错误：",c)},o.onmessage=function(c){if(u.reset().start(),c.data!="Echo message: check"){let t=JSON.parse(c.data),r=Array(19*19).fill(0);if(t.mode===0||t.mode===1||t.mode===2||t.mode===3||t.mode===4){let a=t.message.board;for(let s=0;s<19;s++)for(let i=0;i<19;i++)a[s][i]===-1?r[s*19+i]=0:a[s][i]===0?r[s*19+i]=-1:a[s][i]===1&&(r[s*19+i]=1);e.jsonchessboard=JSON.parse(JSON.stringify(r))}switch(t.mode){case 0:if(e.chessboard=r,e.playerisblack=!e.playerisblack,!e.gamestart&&(e.gamestart=!0,e.showdialog=!1,e.blackplayer.id===e.userid)){let a=e.whiteplayer;e.whiteplayer=e.blackplayer,e.blackplayer=a}break;case 1:if(e.chessboard=r,e.playerisblack=!e.playerisblack,e.isdrop=!0,!e.gamestart&&(e.gamestart=!0,e.showdialog=!1,e.whiteplayer.id===e.userid)){let a=e.blackplayer;e.blackplayer=e.whiteplayer,e.whiteplayer=a}break;case 2:e.chessboard=r;break;case 3:e.chessboard=r;break;case 4:e.chessboard=r;break;case 10:e.roomowner.id=t.message.createUserId,e.roomowner.name=t.message.createUserName,e.roomplayer.id=t.message.secondUserId,d("/api/user/"+e.roomplayer.id,a=>{e.roomplayer.name=a.result.username});break;case 11:e.roomplayer.id="",e.roomplayer.name="",e.roomowner.id=e.userid,e.roomowner.name=e.username,e.isowner=!0,e.showdialog=!0;break}}}}function m(o){e.ws_state&&setTimeout(function(){f(o)},2e3)}window.onbeforeunload=function(){p()};var u={timeout:5e3,timeoutObj:null,serverTimeoutObj:null,reset:function(){return clearTimeout(this.timeoutObj),clearTimeout(this.serverTimeoutObj),this},start:function(){var o=this;this.timeoutObj=setTimeout(function(){n.send("check"),o.serverTimeoutObj=setTimeout(function(){n.close()},o.timeout)},this.timeout)}};export{p as a,f as w};