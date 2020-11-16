 export function getCookie(cname) {
    const name = cname + "="
    const ca = document.cookie.split(';')
    console.log("获取cookie,现在循环")
    for (let i = 0; i < ca.length; i++) {
        const c = ca[i];
        console.log(c)
        while (c.charAt(0) == ' ') c = c.substring(1)
        if (c.indexOf(name) != -1){
            return c.substring(name.length, c.length)
        }
    }
    return undefined;
}


