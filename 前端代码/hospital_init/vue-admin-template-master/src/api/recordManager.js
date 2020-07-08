import request from '@/utils/request'
export function listRecord(data){
    return request({
        url: '/Record/listRecord',
        method: 'get',
        params: data
    })
}
export function addRecord(data){
    return request({
        url: '/Record/addRecord',
        method: 'post',
        data
    })
}
export function updateRecord(data){
    return request({
        url: '/Record/updateRecord',
        method: 'post',
        data
    })
}
export function delRecord(data){
    return request({
        url: '/Record/delRecord',
        method: 'post',
        params:data
    })
}
