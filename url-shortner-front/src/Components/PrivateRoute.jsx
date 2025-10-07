import { useStoreContext } from '../ContextApi/ContextApi'
import { Navigate } from 'react-router-dom'

const PrivateRoute = ({children,publicPage}) => {

const {token} = useStoreContext()

if (publicPage) {
    return token? <Navigate to='/dashboard' /> : children;
}
 return !token?<Navigate to="/login" /> : children;
}

export default PrivateRoute
