pub struct Deque<T> {
    head: usize,
    tail: usize,
    buf: Vec<Option<T>>,
}

impl<T> Deque<T> {
    pub fn new() -> Deque<T> {
        Deque {
            buf: Vec::new(),
            head: 0,
            tail: 0,
        }
    }

    // is the deque empty?
    pub fn is_empty(&self) -> bool {
        self.head == self.tail
    }

    // return the number of items on the deque
    pub fn size(&self) -> usize {
        self.tail - self.head
    }

    // add the item to the front
    pub fn add_first(&mut self, item: T) {
        // head moves then writes
        self.head -= 1;
        self.buf[self.head] = Some(item);
    }

    // add the item to the back
    pub fn add_last(&mut self, item: T) {
        //tail writes then moves
        self.buf[self.tail] = Some(item);
        self.tail += 1;
    }

    // remove and return the item from the front
    pub fn remove_first(&mut self) -> Option<T> {
        // move then remove
        self.head += 1;
        self.buf[self.head].take()
    }

    // remove and return the item from the back
    pub fn remove_last(&mut self) -> Option<T> {
        let t = self.tail;
        self.tail -= 1;
        self.buf[t].take()
    }
}


#[cfg(test)]
mod tests {
    use super::Deque;
    #[test]
    fn it_works() {
        let mut dq = Deque::new();
        assert_eq!(dq.is_empty(), true);
        dq.add_first(1);
        assert_eq!(dq.is_empty(), false);
        assert_eq!(dq.remove_first(), Some(1));
        assert_eq!(dq.is_empty(), true);
        assert_eq!(dq.remove_first(),None);

        assert_eq!(dq.is_empty(), true);
        dq.add_last(1);
        assert_eq!(dq.is_empty(), false);
        assert_eq!(dq.remove_last(),  Some(1));
        assert_eq!(dq.is_empty(), true);
        assert_eq!(dq.remove_last(),None);
    }
}
