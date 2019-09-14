use rand::thread_rng;
use rand::seq::SliceRandom;

fn is_sorted<T>(data: &[T]) -> bool
    where
        T: Ord,
{
    data.windows(2).all(|w| w[0] <= w[1])
}

fn merge<T>(xs:&mut Vec<T>, ys:&mut Vec<T>, low: usize, mid: usize, high: usize)
    where
        T: Ord + Clone + Sized + Copy + std::fmt::Debug,
{
    assert!(is_sorted(&xs[low..mid]));
    assert!(is_sorted(&xs[mid+1..high]));
    ys[low..high].clone_from_slice(&xs[low..high]);

    let mut i = low;
    let mut j = mid + 1;
    for k in low..high {
        if i > mid {
            xs[k] = ys[j];
            j += 1;
        } else if j < high {
            xs[k] = ys[i];
            i += 1;
        } else if ys[j] < ys[i] {
            xs[k] = ys[j];
            j += 1;
        } else {
            xs[k] = ys[i];
            i += 1;
        }
    }
    println!("{:?}", xs);
    assert!(is_sorted(&xs[low..high]));
}

fn mergesort<T>(mut xs: &mut Vec<T>)
    where T: Ord + Clone + Copy + std::fmt::Debug {
    let n = xs.len();
    let mut ys = Vec::clone(xs);

    let mut sz:usize = 1;
    while sz < n {
        let mut lo:usize = 0;
        while lo < n - sz {
            merge(&mut xs, &mut ys, lo, lo + sz - 1, (lo + sz * 2 - 1).min(n -1));
            lo += sz * 2;
        }
        sz = sz * 2;
    }
}



fn main() {
    let mut vec: Vec<u32> = (0..10).collect();
    vec.shuffle(&mut thread_rng());
    mergesort(&mut vec);
}

#[cfg(test)]
mod tests {
    use rand::thread_rng;
    use rand::seq::SliceRandom;
    use super::mergesort;
    #[test]
    fn it_works() {
        let mut vec: Vec<u32> = (0..10).collect();
        vec.shuffle(&mut thread_rng());
        mergesort(vec);
    }}