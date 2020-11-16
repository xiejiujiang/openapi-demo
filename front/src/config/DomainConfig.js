function getDomain(){
    var domain = {
        developer :"https://dev.chanjet.com",
        market:"https://market.chanjet.com"
    };
    // inte-csbank-pay.chanapp.chanjet.com

    var host = window.location.host;
    //prod
    if (host === "csbank-pay.chanapp.chanjet.com" || host === "momi-csbank-pay.chanapp.chanjet.com") {
        domain.developer = "https://dev.chanjet.com",
        domain.market="https://market.chanjet.com"
    } else if (host === "inte-csbank-pay.chanapp.chanjet.com") {
        domain.developer = "http://inte-dev.chanjet.com",
        domain.market="https://inte-market.chanjet.com"
    }else{
        domain.developer = "http://inte-dev.chanjet.com",
        domain.market="https://inte-market.chanjet.com"
    }
    return  domain;
}

export  default { getDomain };