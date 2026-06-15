/*
 * ishigami_function_direct_monte_carlo.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:34 by COMSOL 6.3.0.290. */
public class ishigami_function_direct_monte_carlo {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Uncertainty_Quantification_Module\\Tutorials");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");

    model.param().set("X1", "1");
    model.param().descr("X1", "\u968f\u673a\u53d8\u91cf 1");
    model.param().set("X2", "1");
    model.param().descr("X2", "\u968f\u673a\u53d8\u91cf 2");
    model.param().set("X3", "1");
    model.param().descr("X3", "\u968f\u673a\u53d8\u91cf 3");
    model.param().set("a", "7");
    model.param().descr("a", "Ishigami \u53c2\u6570 1");
    model.param().set("b", "0.1");
    model.param().descr("b", "Ishigami \u53c2\u6570 2");
    model.param().set("M", "a/2");
    model.param().descr("M", "\u5e73\u5747\u503c");
    model.param().set("V", "(a^2)/8+b*(pi^4)/5+b^2*(pi^8)/18+1/2");
    model.param().descr("V", "\u65b9\u5dee");
    model.param().set("STD", "sqrt(V)");
    model.param().descr("STD", "\u6807\u51c6\u5dee");
    model.param().set("par", "1");
    model.param().descr("par", "\u91c7\u6837\u53c2\u6570");
    model.param().set("imax", "8+(pi^4)/10");
    model.param().descr("imax", "\u51fd\u6570 max");
    model.param().set("imin", "-1-(pi^4)/10");
    model.param().descr("imin", "\u51fd\u6570 min");

    model.func().create("rn1", "Random");
    model.func("rn1").label("\u968f\u673a 1 (X1)");
    model.func("rn1").set("uniformrange", "2*pi");
    model.func("rn1").set("seedactive", true);
    model.func().create("rn2", "Random");
    model.func("rn2").label("\u968f\u673a 2 (X2)");
    model.func("rn2").set("uniformrange", "2*pi");
    model.func("rn2").set("seedactive", true);
    model.func().create("rn3", "Random");
    model.func("rn3").label("\u968f\u673a 3 (X3)");
    model.func("rn3").set("uniformrange", "2*pi");
    model.func("rn3").set("seedactive", true);
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "ishigami");
    model.func("an1").set("expr", "sin(x1)+a*(sin(x2))^2+b*x3^4*sin(x1)");
    model.func("an1").set("args", "x1,x2,x3");
    model.func("an1").label("Ishigami \u51fd\u6570");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param")
         .label("\u53c2\u6570\u5316\u626b\u63cf\uff0c\u76f4\u63a5\u8499\u7279\u5361\u7f57");
    model.study("std1").feature("param").setIndex("pname", "X1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "X1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "par", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(1,1,10000)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "ishigami(rn1(par),rn2(par),rn3(par))", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").feature("gev1")
         .label("\u8868\u683c\uff0c\u76f4\u63a5\u8499\u7279\u5361\u7f57");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1")
         .label("\u5e73\u5747\u503c\uff0c\u76f4\u63a5\u8499\u7279\u5361\u7f57");
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "ishigami(rn1(par),rn2(par),rn3(par))", 0);
    model.result().evaluationGroup("eg2").feature("gev1").set("dataseries", "average");
    model.result().evaluationGroup("eg2").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev2")
         .label("\u6807\u51c6\u5dee\uff0c\u76f4\u63a5\u8499\u7279\u5361\u7f57");
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("expr", "ishigami(rn1(par),rn2(par),rn3(par))", 0);
    model.result().evaluationGroup("eg2").feature("gev2").set("dataseries", "stddev");
    model.result().evaluationGroup("eg2").run();
    model.result().dataset().create("kde1", "KernelDensityEstimation");
    model.result().dataset("kde1").set("source", "evaluationgroup");
    model.result().dataset("kde1").set("xaxisdata", 2);
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u76f4\u65b9\u56fe\u548c KDE");
    model.result("pg1").create("tlhs1", "TableHistogram");
    model.result("pg1").feature("tlhs1").set("markerpos", "datapoints");
    model.result("pg1").feature("tlhs1").set("linewidth", "preference");
    model.result("pg1").feature("tlhs1").set("source", "evaluationgroup");
    model.result("pg1").feature("tlhs1").set("xaxisdata", 2);
    model.result("pg1").feature("tlhs1").set("number", 200);
    model.result("pg1").feature("tlhs1").set("normalization", "integralscaled");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("KDE");
    model.result("pg1").feature("lngr1").set("expr", "kde1val");
    model.result("pg1").feature("lngr1").set("data", "kde1");
    model.result("pg1").run();

    model.title("Ishigami \u51fd\u6570\u7684\u76f4\u63a5\u8499\u7279\u5361\u7f57\u4eff\u771f");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9 Ishigami \u51fd\u6570\u6267\u884c\u76f4\u63a5\u8499\u7279\u5361\u7f57\u4eff\u771f\uff0c\u5176\u4e2d\u4e09\u4e2a\u53d8\u91cf\u7684\u968f\u673a\u51fd\u6570\u662f\u4e00\u4e2a\u8457\u540d\u7684\u57fa\u51c6\u51fd\u6570\uff0c\u7528\u4e8e\u6d4b\u8bd5\u5168\u5c40\u7075\u654f\u5ea6\u5206\u6790\u548c\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u7b97\u6cd5\u3002\u5bf9\u4e8e\u672c\u4f8b\u4f7f\u7528\u7684\u8f93\u5165\u5206\u5e03\uff0c\u53ef\u4ee5\u901a\u8fc7\u5206\u6790\u8ba1\u7b97\u5f97\u5230 Ishigami \u51fd\u6570\u7684\u5e73\u5747\u503c\u3001\u6807\u51c6\u5dee\u3001\u6700\u5927\u503c\u548c\u6700\u5c0f\u503c\u3002");

    model.label("ishigami_function_direct_monte_carlo.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
