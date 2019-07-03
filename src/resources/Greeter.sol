pragma experimental ABIEncoderV2;
pragma solidity >=0.4.21;

contract Greeter {
    string public greeting;

    constructor() public  {
        greeting = 'Hello';
    }

    function setGreeting(string memory _greeting) public  {
        greeting = _greeting;
    }

    function greet() view public  returns ( string [] memory ret)  {
        string[] memory b = new string[](2) ;
        b[0] = "hello1";
        b[1] = "world";
        ret = b;
    }
}