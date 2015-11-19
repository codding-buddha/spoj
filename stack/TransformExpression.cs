class TransformExpression
{
    public static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        while (n-- > 0)
        {
            var exp = Console.ReadLine();
            Console.WriteLine(GetPostFix(exp));
        }
    }

    static string GetPostFix(string exp)
    {
        var opdStack = new Stack<string>();
        var oprStack = new Stack<char>();
        foreach (var c in exp)
        {
            if (IsOperator(c))
            {
                if (c == ')')
                {
                    while (oprStack.Peek() != '(')
                    {
                        Apply(oprStack, opdStack);
                    }
                    oprStack.Pop();
                    continue;
                }

                if (oprStack.Count > 0 && c != '(' && GetPriority(oprStack.Peek()) > GetPriority(c))
                {
                    Apply(oprStack, opdStack);
                    oprStack.Push(c);
                }
                else
                {
                    oprStack.Push(c);
                }
            }
            else
            {
                opdStack.Push(c.ToString());
            }
        }

        while (opdStack.Count >= 2)
        {
            Apply(oprStack, opdStack);
        }

        return opdStack.Pop();
    }

    static bool IsOperator(char c)
    {
        return c == '*' || c == '/' || c == '+' || c == '-' || c == '^' || c == '(' || c == ')';
    }

    static void Apply(Stack<char> oprStack, Stack<string> opdStack)
    {
        var opd1 = opdStack.Pop();
        var opd2 = opdStack.Pop();
        var opr = oprStack.Pop();
        opdStack.Push(opd2 + opd1 + opr);
    }

    static int GetPriority(char c)
    {
        switch (c)
        {
            case '+': return 0;
            case '-': return 1;
            case '*': return 2;
            case '/': return 3;
            case '^': return 4;
            case '(': return -1;
            default: return 6;
        }
    }
}