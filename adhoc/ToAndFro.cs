class ToAndFro
{
	public static void Main(string[] args) 
	{
        var colCount = 0;
        while ((colCount = int.Parse(Console.ReadLine())) != 0)
        {
            var emsg = Console.ReadLine();
            var rowCount = emsg.Length / colCount;
            var omsg = new char[emsg.Length];
            var k = 0;
            for (var i = 0; i < colCount; i++)
            {
                for (var j = 0; j < rowCount; j++)
                {
                    var pos = j * colCount;
                    if (j % 2 == 0)
                        omsg[k++] = emsg[pos + i];
                    else
                        omsg[k++] = emsg[pos + (colCount - i - 1)];
                }
            }
            Console.WriteLine(new String(omsg));
        }	
    }
}