/*
 * benzoic_acid_crystallization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:19 by COMSOL 6.3.0.290. */
public class benzoic_acid_crystallization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("ge", "GlobalEquations");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "30[degC]", "MSMPR \u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("C0", "228[g/kg]", "\u8fdb\u6599\u6eb6\u8d28\u6d53\u5ea6");
    model.param().set("tau", "45 [min]", "\u505c\u7559\u65f6\u95f4");
    model.param().set("k_v", "0.1", "\u6676\u4f53\u4f53\u79ef\u5f62\u72b6\u56e0\u5b50");
    model.param().set("rho_c", "1.32[g/cm^3]", "\u6676\u4f53\u5bc6\u5ea6");
    model.param().set("k_b", "9.16e12[(1/(m^3*s))/((g/cm^3)*(g/g))]", "\u6210\u6838\u901f\u7387\u7cfb\u6570");
    model.param().set("E_g", "40.05[kJ/mol]", "\u6676\u4f53\u751f\u957f\u7684\u6d3b\u5316\u80fd");
    model.param().set("k_g0", "1.06e7[(um/s)/(g/g)]", "\u589e\u957f\u7387\u7684\u6307\u524d\u56e0\u5b50");
    model.param().set("g", "0.44", "\u6676\u4f53\u751f\u957f\u9a71\u52a8\u529b\u7684\u6307\u6570");
    model.param()
         .set("j_const", "1.78", "\u60ac\u6d6e\u7269\u5bc6\u5ea6\u5bf9\u6210\u6838\u7387\u5f71\u54cd\u7684\u6307\u6570");
    model.param().set("b", "1.2", "\u6210\u6838\u9a71\u52a8\u529b\u7684\u6307\u6570");
    model.param().set("L_max", "2500[um]", "CSD \u4e2d\u5305\u542b\u7684\u6700\u5927\u5927\u5c0f");
    model.param().set("L_int", "1[m]", "\u5f62\u5f0f\u79ef\u5206\u5e38\u6570");
    model.param("default").paramCase().create("case1");
    model.param("default").paramCase("case1").label("\u7ed9\u51fa\u6700\u5927\u6676\u4f53\u7684\u5b9e\u9a8c");
    model.param("default").paramCase().create("case2");
    model.param("default").paramCase("case2").label("\u7ed9\u51fa\u6700\u5c0f\u6676\u4f53\u7684\u5b9e\u9a8c");
    model.param("default").paramCase("case2").set("T", "0[degC]");
    model.param("default").paramCase("case2").set("tau", "20[min]");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u9971\u548c\u8d28\u91cf\u5206\u6570");
    model.func("an1").set("funcname", "C_star");
    model.func("an1").set("expr", "2.03e-5*T^4 + 2.97e-4*T^3 + 4.70e-2*T^2 + 1.43*T + 24.71");
    model.func("an1").set("args", "T");
    model.func("an1").set("fununit", "g/kg");
    model.func("an1").setIndex("argunit", "degC", 0);
    model.func("an1").setIndex("plotargs", "-15[degC]", 0, 1);
    model.func("an1").setIndex("plotargs", "40[degC]", 0, 2);
    model.func("an1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u9971\u548c\u8d28\u91cf\u5206\u6570");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u9971\u548c\u8d28\u91cf\u5206\u6570");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "C<sup>*</sup>(T) (g/kg)");
    model.result("pg1").run();

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("M_T_MB", "C0-C", "MSMPR \u6676\u4f53\u91cd\u91cf\u5206\u6570\uff0c\u6765\u81ea\u8d28\u91cf\u5e73\u8861");
    model.component("comp1").variable("var1")
         .set("M_T_MR", "6 * k_v * rho_c * n0 * (G * tau)^4", "MSMPR \u6676\u4f53\u91cd\u91cf\u5206\u6570\uff0c\u6765\u81ea\u529b\u77e9\u5173\u7cfb");
    model.component("comp1").variable("var1").set("n0", "B0/G", "\u6838\u7fa4\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("B0", "k_b * M_T_MB^j_const * DeltaC^b", "\u7a33\u6001\u4e0b\u7684\u6210\u6838\u901f\u7387");
    model.component("comp1").variable("var1").set("DeltaC", "C - C_star(T)", "\u7ed3\u6676\u7684\u9a71\u52a8\u529b");
    model.component("comp1").variable("var1")
         .set("G", "k_g * DeltaC^g", "\u7a33\u6001\u4e0b\u7684\u6676\u4f53\u589e\u957f\u7387");
    model.component("comp1").variable("var1")
         .set("k_g", "k_g0*exp(-E_g/(R_const*T))", "\u6676\u4f53\u589e\u957f\u7387\u7cfb\u6570");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "C", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "M_T_MB-M_T_MR", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "C_star(T)+1[g/kg]", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "\u82ef\u7532\u9178\u8d28\u91cf\u5206\u6570", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "g/kg", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "g/kg", 0, 0);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6781\u7aef\u6848\u4f8b");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "range(1,1,2)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"C"});
    model.result().numerical("gev1").set("descr", new String[]{"\u82ef\u7532\u9178\u8d28\u91cf\u5206\u6570"});
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").label("\u4e00\u7ef4\u6805\u683c\uff1a\u6781\u7aef\u6848\u4f8b");
    model.result().dataset("grid1").set("par1", "L");
    model.result().dataset("grid1").set("data", "dset2");
    model.result().dataset("grid1").set("parmax1", "L_max");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6781\u7aef\u6848\u4f8b\u7684 CSD");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u57fa\u4e8e\u4f53\u79ef\u7684\u5e73\u5747\u6676\u4f53\u7c92\u5ea6\u5206\u5e03");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6676\u4f53\u7c92\u5ea6\u5206\u5e03 (%)");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1")
         .set("expr", "(n0 * exp(- L / (G*tau))*L^4)/ integrate((n0 * exp(- L / (G*tau)))*L^3, L, 0, L_max)");
    model.result("pg2").feature("lngr1").set("unit", "%");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "L");
    model.result("pg2").feature("lngr1").set("xdataunit", "\u00b5m");
    model.result("pg2").feature("lngr1").set("xdatadescractive", true);
    model.result("pg2").feature("lngr1").set("xdatadescr", "\u6676\u4f53\u7c92\u5ea6");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "T =20\\deg C, \\tau = 45 min", 0);
    model.result("pg2").feature("lngr1").setIndex("legends", "T = 0\\deg C, \\tau = 15 min", 1);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("lngr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").feature("gmrk1").set("precision", 3);
    model.result("pg2").feature("lngr1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg2").feature("lngr1").feature("gmrk1").set("inclycoord", false);
    model.result("pg2").feature("lngr1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 10);
    model.result("pg2").set("ymax", 90);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u6e29\u5ea6\u626b\u63cf");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,1,30)", 0);
    model.study("std2").feature("stat").setIndex("punit", "degC", 0);
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "0.0001");
    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").set("expr", new String[]{"C"});
    model.result().numerical("gev2").set("descr", new String[]{"\u82ef\u7532\u9178\u8d28\u91cf\u5206\u6570"});
    model.result().dataset().duplicate("grid2", "grid1");
    model.result().dataset("grid2").label("\u4e00\u7ef4\u6805\u683c\uff1a\u6e29\u5ea6\u626b\u63cf");
    model.result().dataset("grid2").set("data", "dset3");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7528\u4e8e\u6e29\u5ea6\u626b\u63cf\u7684 CSD");
    model.result("pg3").set("data", "grid2");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{1, 16, 31}, 0);
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u505c\u7559\u65f6\u95f4\u626b\u63cf");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "tau", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(15,1,45)", 0);
    model.study("std3").feature("stat").setIndex("punit", "min", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "dset4");
    model.result().numerical("gev3").set("expr", new String[]{"C"});
    model.result().numerical("gev3").set("descr", new String[]{"\u82ef\u7532\u9178\u8d28\u91cf\u5206\u6570"});
    model.result().dataset().duplicate("grid3", "grid2");
    model.result().dataset("grid3").label("\u4e00\u7ef4\u6805\u683c\uff1a\u505c\u7559\u65f6\u95f4\u626b\u63cf");
    model.result().dataset("grid3").set("data", "dset4");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u505c\u7559\u65f6\u95f4\u626b\u63cf\u7684 CSD");
    model.result("pg4").set("data", "grid3");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").feature("gmrk1").set("orientation", "vertical");
    model.result("pg4").feature("lngr1").feature("gmrk1").set("anchorpoint", "middleleft");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e0d\u540c\u5de5\u4f5c\u6e29\u5ea6\u4e0b\u7684\u5e73\u5747\u7c92\u5f84");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1")
         .setIndex("expr", "integrate(n0 * exp(- L_int / (G*tau))*L_int^4, L_int, 0, L_max) / integrate((n0 * exp(- L_int / (G*tau)))*L_int^3, L_int, 0, L_max)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "um", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5e73\u5747\u7c92\u5f84", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "T");
    model.result("pg5").feature("glob1").set("xdataunit", "degC");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u4e0d\u540c\u505c\u7559\u65f6\u95f4\u65f6\u7684\u5e73\u5747\u7c92\u5f84");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("xdataexpr", "tau");
    model.result("pg6").feature("glob1").set("xdataunit", "min");
    model.result("pg6").run();

    model
         .title("\u82ef\u7532\u9178\u5728\u6df7\u5408\u60ac\u6d6e\u6df7\u5408\u4ea7\u7269\u6392\u51fa\u7ed3\u6676\u5668\u4e2d\u7684\u7ed3\u6676");

    model
         .description("\u7ed3\u6676\u662f\u836f\u54c1\u751f\u4ea7\u7b49\u5de5\u827a\u4e2d\u7684\u4e00\u4e2a\u5173\u952e\u5206\u79bb\u8fc7\u7a0b\uff0c\u901a\u8fc7\u5f62\u6210\u6676\u4f53\u5c06\u5316\u5b66\u7269\u8d28\u4ece\u6eb6\u6db2\u4e2d\u5206\u79bb\u51fa\u6765\u3002\u4e3a\u4e86\u8fbe\u5230\u6240\u9700\u7684\u4ea7\u54c1\u5c5e\u6027\uff0c\u5fc5\u987b\u5bf9\u6676\u4f53\u7c92\u5f84\u5206\u5e03\u8fdb\u884c\u63a7\u5236\u3002\u8fd9\u4e2a\u96f6\u7ef4\u6a21\u578b\u901a\u8fc7\u5b9e\u73b0\u7fa4\u4f53\u5e73\u8861\u65b9\u7a0b\u6765\u786e\u5b9a\u5728\u6df7\u5408\u60ac\u6d6e\u6df7\u5408\u4ea7\u7269\u6392\u51fa\u7ed3\u6676\u5668\u4e2d\u7ed3\u6676\u7684\u82ef\u7532\u9178\u6676\u4f53\u7684\u7c92\u5f84\u5206\u5e03\u548c\u5e73\u5747\u7c92\u5f84\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("benzoic_acid_crystallization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
