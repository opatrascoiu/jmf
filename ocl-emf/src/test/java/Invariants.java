package test.scripts;

import java.util.*;

import uk.ac.kent.cs.ocl20.standard.lib.*;
import uk.ac.kent.cs.ocl20.bridge4emf.*;

public class Invariants {
	public static class Library {
		public static Object inv$0(library.Library self) {
			try {
				// return result
				if (self != null) return self;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$1(library.Library self) {
			try {
				// Call property 'books'
				OclSet t1 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// return result
				if (t1 != null) return t1;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$2(library.Library self) {
			try {
				// Call property 'books'
				OclSet t3 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'size'
				OclInteger t2 = (OclInteger)t3.size();
				// return result
				if (t2 != null) return t2;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$3(library.Library self) {
			try {
				// Call property 'books'
				OclSet t6 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'size'
				OclInteger t5 = (OclInteger)t6.size();
				// Call operation 'greaterThan'
				OclBoolean t4 = (OclBoolean)t5.greaterThan(StdLibAdapterImpl.INSTANCE.Integer(100));
				// return result
				if (t4 != null) return t4;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$4(library.Library self) {
			try {
				// Call property 'books'
				OclSet t9 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t8 = (OclSequence)t9.asSequence();
				// Call operation 'first'
				library.Book t7 = (library.Book)t8.first();
				// return result
				if (t7 != null) return t7;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$5(library.Library self) {
			try {
				// Call property 'books'
				OclSet t13 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t12 = (OclSequence)t13.asSequence();
				// Call operation 'first'
				library.Book t11 = (library.Book)t12.first();
				// Call property 'title'
				OclString t10 = StdLibAdapterImpl.INSTANCE.String(t11.getTitle());
				// return result
				if (t10 != null) return t10;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$6(library.Library self) {
			try {
				// Call property 'books'
				OclSet t17 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t16 = (OclSequence)t17.asSequence();
				// Call operation 'first'
				library.Book t15 = (library.Book)t16.first();
				// Call property 'author'
				library.Author t14 = (library.Author)t15.getAuthor();
				// return result
				if (t14 != null) return t14;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$7(library.Library self) {
			try {
				// Call property 'books'
				OclSet t22 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t21 = (OclSequence)t22.asSequence();
				// Call operation 'first'
				library.Book t20 = (library.Book)t21.first();
				// Call property 'author'
				library.Author t19 = (library.Author)t20.getAuthor();
				// Call property 'name'
				OclString t18 = StdLibAdapterImpl.INSTANCE.String(t19.getName());
				// return result
				if (t18 != null) return t18;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$8(library.Library self) {
			try {
				// Call property 'books'
				OclSet t26 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t25 = (OclSequence)t26.asSequence();
				// Call operation 'first'
				library.Book t24 = (library.Book)t25.first();
				// Call property 'category'
				library.BookCategory t23 = (library.BookCategory)t24.getCategory();
				// return result
				if (t23 != null) return t23;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$9(library.Library self) {
			try {
				// return result
				if (library.BookCategory.get("ScienceFiction") != null) return library.BookCategory.get("ScienceFiction");
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$10(library.Library self) {
			try {
				// return result
				if (library.BookCategory.get("Biography") != null) return library.BookCategory.get("Biography");
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$11(library.Library self) {
			try {
				// Call operation 'equalTo'
				OclBoolean t27 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(library.BookCategory.get("ScienceFiction"), library.BookCategory.get("ScienceFiction")));
				// return result
				if (t27 != null) return t27;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$12(library.Library self) {
			try {
				// Call operation 'equalTo'
				OclBoolean t28 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(library.BookCategory.get("ScienceFiction"), library.BookCategory.get("Biography")));
				// return result
				if (t28 != null) return t28;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$13(library.Library self) {
			try {
				// Call operation 'notEqualTo'
				OclBoolean t29 = StdLibAdapterImpl.INSTANCE.Boolean(!EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(library.BookCategory.get("ScienceFiction"), library.BookCategory.get("ScienceFiction")));
				// return result
				if (t29 != null) return t29;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$14(library.Library self) {
			try {
				// Call operation 'notEqualTo'
				OclBoolean t30 = StdLibAdapterImpl.INSTANCE.Boolean(!EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(library.BookCategory.get("ScienceFiction"), library.BookCategory.get("Biography")));
				// return result
				if (t30 != null) return t30;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$15(library.Library self) {
			try {
				// Call property 'books'
				OclSet t33 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t32 = (OclSequence)t33.asSequence();
				// Call operation 'first'
				library.Book t31 = (library.Book)t32.first();
				library.Book book = t31;
				// return result
				if (book != null) return book;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$16(library.Library self) {
			try {
				// Call property 'books'
				OclSet t36 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t35 = (OclSequence)t36.asSequence();
				// Call operation 'first'
				library.Book t34 = (library.Book)t35.first();
				library.Book book = t34;
				// Call property 'title'
				OclString t37 = StdLibAdapterImpl.INSTANCE.String(book.getTitle());
				OclString title = t37;
				// return result
				if (title != null) return title;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$17(library.Library self) {
			try {
				// Call property 'books'
				OclSet t40 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t39 = (OclSequence)t40.asSequence();
				// Call operation 'first'
				library.Book t38 = (library.Book)t39.first();
				library.Book book = t38;
				// Call property 'category'
				library.BookCategory t41 = (library.BookCategory)book.getCategory();
				library.BookCategory category = t41;
				// return result
				if (category != null) return category;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$18(library.Library self) {
			try {
				// Call property 'books'
				OclSet t44 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t43 = (OclSequence)t44.asSequence();
				// Call operation 'first'
				library.Book t42 = (library.Book)t43.first();
				library.Book book = t42;
				// Call property 'category'
				library.BookCategory t45 = (library.BookCategory)book.getCategory();
				library.BookCategory category = t45;
				OclTuple t46 = StdLibAdapterImpl.INSTANCE.Tuple(new HashMap());
				t46.setProperty(StdLibAdapterImpl.INSTANCE.String("category"), category);
				// Call operation 'equalTo'
				OclBoolean t47 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(category, library.BookCategory.get("Biography")));
				t46.setProperty(StdLibAdapterImpl.INSTANCE.String("comparison"), t47);
				// return result
				if (t46 != null) return t46;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$19(library.Library self) {
			try {
				// Call property 'books'
				OclSet t51 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t50 = (OclSequence)t51.asSequence();
				// Call operation 'first'
				library.Book t49 = (library.Book)t50.first();
				// Call property 'books'
				OclSet t54 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t53 = (OclSequence)t54.asSequence();
				// Call operation 'last'
				library.Book t52 = (library.Book)t53.last();
				// Call operation 'equalTo'
				OclBoolean t48 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.OclModelElement_equalTo(t49, t52));
				// return result
				if (t48 != null) return t48;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$20(library.Library self) {
			try {
				// Call property 'books'
				OclSet t57 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t56 = (OclSequence)t57.asSequence();
				// Call operation 'first'
				library.Book t55 = (library.Book)t56.first();
				library.Book b1 = t55;
				// Call property 'books'
				OclSet t60 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t59 = (OclSequence)t60.asSequence();
				// Call operation 'last'
				library.Book t58 = (library.Book)t59.last();
				library.Book b2 = t58;
				// Call operation 'equalTo'
				OclBoolean t61 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.OclModelElement_equalTo(b1, b2));
				// return result
				if (t61 != null) return t61;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$21(library.Library self) {
			try {
				// Call property 'books'
				OclSet t64 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t63 = (OclSequence)t64.asSequence();
				// Call operation 'first'
				library.Book t62 = (library.Book)t63.first();
				library.Book b1 = t62;
				// Call property 'books'
				OclSet t67 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t66 = (OclSequence)t67.asSequence();
				// Call operation 'first'
				library.Book t65 = (library.Book)t66.first();
				library.Book b2 = t65;
				// Call operation 'equalTo'
				OclBoolean t68 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.OclModelElement_equalTo(b1, b2));
				// return result
				if (t68 != null) return t68;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$22(library.Library self) {
			try {
				// Call property 'books'
				OclSet t71 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'size'
				OclInteger t70 = (OclInteger)t71.size();
				// Call operation 'greaterThan'
				OclBoolean t69 = (OclBoolean)t70.greaterThan(StdLibAdapterImpl.INSTANCE.Integer(100));
				// return result
				if (t69 != null) return t69;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$23(library.Library self) {
			try {
				// Call property 'books'
				OclSet t76 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t75 = (OclSequence)t76.asSequence();
				// Call operation 'first'
				library.Book t74 = (library.Book)t75.first();
				// Call property 'category'
				library.BookCategory t73 = (library.BookCategory)t74.getCategory();
				// Call operation 'equalTo'
				OclBoolean t72 = StdLibAdapterImpl.INSTANCE.Boolean(EmfImplementationAdapter.INSTANCE.EnumLiteral_equalTo(t73, library.BookCategory.get("Biography")));
				// return result
				if (t72 != null) return t72;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$24(library.Library self) {
			try {
				// 'any' iterator
				// Call property 'books'
				OclSet t77 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				library.Book b = null;
				//--- Init result ---
				library.Book t78 = null;
				//--- For each element from collection ---
				java.util.Iterator t79 = StdLibAdapterImpl.INSTANCE.impl(t77).iterator();
				while (t79.hasNext()) {
				    b = (library.Book)t79.next();
				    //--- Compute body ---
				    // Call property 'title'
				    OclString t81 = StdLibAdapterImpl.INSTANCE.String(b.getTitle());
				    // Call operation 'equalTo'
				    OclBoolean t80 = StdLibAdapterImpl.INSTANCE.Boolean(t81.equals(StdLibAdapterImpl.INSTANCE.String("No Author")));
				    //--- Break the loop ---
				    if(StdLibAdapterImpl.INSTANCE.impl(t80).booleanValue()) {
				        t78 = b;
				        break;
				    }
				}
				library.Book book = t78;
				OclSet t83 = StdLibAdapterImpl.INSTANCE.Set();
				t83 = t83.including(book);
				// Call operation 'isEmpty'
				OclBoolean t82 = (OclBoolean)t83.isEmpty();
				// return result
				if (t82 != null) return t82;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$25(library.Library self) {
			try {
				// 'any' iterator
				// Call property 'books'
				OclSet t84 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				library.Book b = null;
				//--- Init result ---
				library.Book t85 = null;
				//--- For each element from collection ---
				java.util.Iterator t86 = StdLibAdapterImpl.INSTANCE.impl(t84).iterator();
				while (t86.hasNext()) {
				    b = (library.Book)t86.next();
				    //--- Compute body ---
				    // Call property 'title'
				    OclString t88 = StdLibAdapterImpl.INSTANCE.String(b.getTitle());
				    // Call operation 'equalTo'
				    OclBoolean t87 = StdLibAdapterImpl.INSTANCE.Boolean(t88.equals(StdLibAdapterImpl.INSTANCE.String("No Author")));
				    //--- Break the loop ---
				    if(StdLibAdapterImpl.INSTANCE.impl(t87).booleanValue()) {
				        t85 = b;
				        break;
				    }
				}
				library.Book book = t85;
				// return result
				if (book != null) return book;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$26(library.Library self) {
			try {
				// 'any' iterator
				// Call property 'books'
				OclSet t89 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				library.Book b = null;
				//--- Init result ---
				library.Book t90 = null;
				//--- For each element from collection ---
				java.util.Iterator t91 = StdLibAdapterImpl.INSTANCE.impl(t89).iterator();
				while (t91.hasNext()) {
				    b = (library.Book)t91.next();
				    //--- Compute body ---
				    // Call property 'title'
				    OclString t93 = StdLibAdapterImpl.INSTANCE.String(b.getTitle());
				    // Call operation 'equalTo'
				    OclBoolean t92 = StdLibAdapterImpl.INSTANCE.Boolean(t93.equals(StdLibAdapterImpl.INSTANCE.String("No Author")));
				    //--- Break the loop ---
				    if(StdLibAdapterImpl.INSTANCE.impl(t92).booleanValue()) {
				        t90 = b;
				        break;
				    }
				}
				library.Book book = t90;
				// Call property 'author'
				library.Author t94 = (library.Author)book.getAuthor();
				// return result
				if (t94 != null) return t94;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$27(library.Library self) {
			try {
				// 'any' iterator
				// Call property 'books'
				OclSet t95 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				library.Book b = null;
				//--- Init result ---
				library.Book t96 = null;
				//--- For each element from collection ---
				java.util.Iterator t97 = StdLibAdapterImpl.INSTANCE.impl(t95).iterator();
				while (t97.hasNext()) {
				    b = (library.Book)t97.next();
				    //--- Compute body ---
				    // Call property 'title'
				    OclString t99 = StdLibAdapterImpl.INSTANCE.String(b.getTitle());
				    // Call operation 'equalTo'
				    OclBoolean t98 = StdLibAdapterImpl.INSTANCE.Boolean(t99.equals(StdLibAdapterImpl.INSTANCE.String("No Author")));
				    //--- Break the loop ---
				    if(StdLibAdapterImpl.INSTANCE.impl(t98).booleanValue()) {
				        t96 = b;
				        break;
				    }
				}
				library.Book book = t96;
				// Call property 'author'
				library.Author t101 = (library.Author)book.getAuthor();
				// Call property 'name'
				OclString t100 = StdLibAdapterImpl.INSTANCE.String(t101.getName());
				// return result
				if (t100 != null) return t100;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static Object inv$28(library.Library self) {
			try {
				// 'any' iterator
				// Call property 'books'
				OclSet t102 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				library.Book b = null;
				//--- Init result ---
				library.Book t103 = null;
				//--- For each element from collection ---
				java.util.Iterator t104 = StdLibAdapterImpl.INSTANCE.impl(t102).iterator();
				while (t104.hasNext()) {
				    b = (library.Book)t104.next();
				    //--- Compute body ---
				    // Call property 'title'
				    OclString t106 = StdLibAdapterImpl.INSTANCE.String(b.getTitle());
				    // Call operation 'equalTo'
				    OclBoolean t105 = StdLibAdapterImpl.INSTANCE.Boolean(t106.equals(StdLibAdapterImpl.INSTANCE.String("No Author")));
				    //--- Break the loop ---
				    if(StdLibAdapterImpl.INSTANCE.impl(t105).booleanValue()) {
				        t103 = b;
				        break;
				    }
				}
				library.Book b1 = t103;
				// Call property 'books'
				OclSet t109 = StdLibAdapterImpl.INSTANCE.Set(self.getBooks());
				// Call operation 'asSequence'
				OclSequence t108 = (OclSequence)t109.asSequence();
				// Call operation 'last'
				library.Book t107 = (library.Book)t108.last();
				library.Book b2 = t107;
				// Call property 'author'
				library.Author t112 = (library.Author)b1.getAuthor();
				// Call property 'name'
				OclString t111 = StdLibAdapterImpl.INSTANCE.String(t112.getName());
				// Call property 'author'
				library.Author t114 = (library.Author)b2.getAuthor();
				// Call property 'name'
				OclString t113 = StdLibAdapterImpl.INSTANCE.String(t114.getName());
				// Call operation 'equalTo'
				OclBoolean t110 = StdLibAdapterImpl.INSTANCE.Boolean(t111.equals(t113));
				// return result
				if (t110 != null) return t110;
				else return StdLibAdapterImpl.INSTANCE.Undefined();
			} catch (Exception e) {
				return StdLibAdapterImpl.INSTANCE.Undefined();
			}
		}
		public static List evaluateAll(library.Library self) {
			List result = new Vector();
			result.add(inv$0(self));
			result.add(inv$1(self));
			result.add(inv$2(self));
			result.add(inv$3(self));
			result.add(inv$4(self));
			result.add(inv$5(self));
			result.add(inv$6(self));
			result.add(inv$7(self));
			result.add(inv$8(self));
			result.add(inv$9(self));
			result.add(inv$10(self));
			result.add(inv$11(self));
			result.add(inv$12(self));
			result.add(inv$13(self));
			result.add(inv$14(self));
			result.add(inv$15(self));
			result.add(inv$16(self));
			result.add(inv$17(self));
			result.add(inv$18(self));
			result.add(inv$19(self));
			result.add(inv$20(self));
			result.add(inv$21(self));
			result.add(inv$22(self));
			result.add(inv$23(self));
			result.add(inv$24(self));
			result.add(inv$25(self));
			result.add(inv$26(self));
			result.add(inv$27(self));
			result.add(inv$28(self));
			return result;
		}
	}
}
