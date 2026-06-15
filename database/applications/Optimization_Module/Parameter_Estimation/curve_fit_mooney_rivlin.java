/*
 * curve_fit_mooney_rivlin.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:41 by COMSOL 6.3.0.290. */
public class curve_fit_mooney_rivlin {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Parameter_Estimation");

    model.component().create("comp1", true);

    model.param().set("lambda", "1");
    model.param().descr("lambda", "\u4f38\u957f\u7387");
    model.param().set("C10", "1[MPa]");
    model.param().descr("C10", "Mooney-Rivlin \u53c2\u6570");
    model.param().set("C01", "1[MPa]");
    model.param().descr("C01", "Mooney-Rivlin \u53c2\u6570");

    model.variable().create("var1");
    model.variable("var1").set("P", "2*(C10+C01/lambda)*(lambda-1/lambda^2)");
    model.variable("var1").descr("P", "\u5de5\u7a0b\u5e94\u529b");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").set("filename", "curve_fit_mooney_rivlin.csv");
    model.study("std1").feature("lsqo").importData();
    model.study("std1").feature("lsqo").setEntry("columnType", "col1", "param");
    model.study("std1").feature("lsqo").setEntry("parameterName", "col1", "lambda");
    model.study("std1").feature("lsqo").setEntry("parameterUnit", "col1", "1");
    model.study("std1").feature("lsqo").setEntry("modelExpression", "col2", "P");
    model.study("std1").feature("lsqo").setEntry("variableName", "col2", "engStress");
    model.study("std1").feature("lsqo").setEntry("unit", "col2", "Pa");
    model.study("std1").feature("lsqo").setIndex("pname", "lambda", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 1, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "lambda", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 1, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "C10", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1[MPa]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "C10", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1[MPa]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "C01", 0);
    model.study("std1").feature("lsqo").setIndex("scale", "1[MPa]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", "1[MPa]", 1);
    model.study("std1").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study("std1").feature("lsqo").set("probewindow", "");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"P"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5de5\u7a0b\u5e94\u529b"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"Pa"});
    model.result("pg1").feature("glob1").setIndex("expr", "P*lambda", 1);
    model.result("pg1").feature("glob1").setIndex("descr", "\u771f\uff08\u67ef\u897f\uff09\u5e94\u529b", 1);
    model.result("pg1").feature("glob1").setIndex("expr", "P/lambda", 2);
    model.result("pg1").feature("glob1")
         .setIndex("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b", 2);
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").set("expr", new String[]{"opt.glsobj.engStress.data"});
    model.result("pg1").feature("glob2").set("descr", new String[]{"\u6700\u5c0f\u4e8c\u4e58\u5b9e\u9a8c\u503c"});
    model.result("pg1").feature("glob2").set("linestyle", "none");
    model.result("pg1").feature("glob2").set("linemarker", "circle");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u4f38\u957f\u7387");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5e94\u529b");
    model.result("pg1").set("legendpos", "upperleft");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result("pg1").run();
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"C10"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"Mooney-Rivlin \u53c2\u6570"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"C10", "C01"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"Mooney-Rivlin \u53c2\u6570", "Mooney-Rivlin \u53c2\u6570"});
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("Mooney-Rivlin \u66f2\u7ebf\u62df\u5408");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u4f18\u5316\u6a21\u5757\u201d\u6765\u6839\u636e\u5b9e\u9a8c\u6570\u636e\u62df\u5408\u6750\u6599\u6a21\u578b\u66f2\u7ebf\u3002");

    model.label("curve_fit_mooney_rivlin.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
