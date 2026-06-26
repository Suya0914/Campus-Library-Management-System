const API = {
    async request(url, opt={}) {
        const cfg = { headers:{'Content-Type':'application/json'}, ...opt };
        try{ const r=await fetch(url,cfg); if(!r.ok) throw Error('HTTP '+r.status); return await r.json(); }
        catch(e){ return {success:false, message:'网络错误: '+e.message}; }
    },
    post(u,d){return this.request(u,{method:'POST',body:JSON.stringify(d)});},
    get(u){return this.request(u,{method:'GET'});},
    addBook(d){return this.post('/api/book/add',d);},
    outBook(d){return this.post('/api/book/out',d);},
    searchBooks(d){return this.post('/api/book/search',d);},
    listBooks(){return this.get('/api/book/list');},
    addReader(d){return this.post('/api/reader/add',d);},
    listReaders(){return this.get('/api/reader/list');},
    borrowBook(d){return this.post('/api/operate/borrow',d);},
    returnBook(d){return this.post('/api/operate/return',d);},
    login(d){return this.post('/api/auth/login',d);},
    logout(){return this.post('/api/auth/logout',{});},
    checkAuth(){return this.get('/api/auth/status');}
};

function showMessage(eid,msg,ok){
    const el=document.getElementById(eid); if(!el)return;
    el.textContent=msg; el.className='message-box '+(ok?'success':'error'); el.style.display='block';
}

async function updateNavAuth(){
    const r=await API.checkAuth();
    const li=document.getElementById('navLogin'), lo=document.getElementById('navLogout');
    if(!li&&!lo)return;
    if(r&&r.success){ if(li)li.style.display='none'; if(lo){lo.style.display='inline';lo.textContent='退出 ('+r.data+')';} }
    else{ if(li)li.style.display='inline'; if(lo)lo.style.display='none'; }
}

async function handleLogout(){ const r=await API.logout(); if(r.success){await updateNavAuth();window.location.href='index.html';} }
