import request from '@/utils/request'
export function listRecord(data){
    return request({
        url: '/doctor/listRecord',
        method: 'get',
        params: data
    })
}
export function addDoctor(data){
    return request({
        url: '/doctor/addDoctor',
        method: 'post',
        data
    })
}
export function updateDoctor(data){
    return request({
        url: '/doctor/updateDoctor',
        method: 'post',
        data
    })
}

export function delDoctor(data){
    return request({
        url: '/doctor/delDoctor',
        method: 'post',
        params:data
    })
}