val str = "1 3"
val array = str.split(" ")
(array.headOption, array.lastOption) match {
  case (Some(a), Some(b)) => ValidCoord
    else _=> InvalidCoord()
}
