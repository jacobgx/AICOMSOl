/*
 * pid_control.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:39 by COMSOL 6.3.0.290. */
public class pid_control {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.param().set("v_in_top", "0.01[m/s]");
    model.param().descr("v_in_top", "\u901f\u5ea6\uff0c\u4e0a\u5165\u53e3");
    model.param().set("c_in_top", "1[mol/m^3]");
    model.param().descr("c_in_top", "\u6d53\u5ea6\uff0c\u4e0a\u5165\u53e3");
    model.param().set("c_in_inlet", "0.2[mol/m^3]");
    model.param().descr("c_in_inlet", "\u6d53\u5ea6\uff0c\u53ef\u63a7\u5165\u53e3");
    model.param().set("c00", "0.5[mol/m^3]");
    model.param().descr("c00", "\u521d\u59cb\u6d53\u5ea6\uff0c\u53cd\u5e94\u5ba4\u5185\u90e8");
    model.param().set("D", "1e-4[m^2/s]");
    model.param().descr("D", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_set", "0.5[mol/m^3]");
    model.param().descr("c_set", "\u8bbe\u5b9a\u70b9\u6d53\u5ea6");
    model.param().set("k_P_ctrl", "-0.5[m^4/(mol*s)]");
    model.param().descr("k_P_ctrl", "\u6bd4\u4f8b\u53c2\u6570");
    model.param().set("k_I_ctrl", "-1[m^4/(mol*s^2)]");
    model.param().descr("k_I_ctrl", "\u79ef\u5206\u53c2\u6570");
    model.param().set("k_D_ctrl", "-1e-3[m^4/mol]");
    model.param().descr("k_D_ctrl", "\u5fae\u5206\u53c2\u6570");

    model.component("comp1").geom("geom1").insertFile("pid_control_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", -0.002, 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "c_mp");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "c");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.component("comp1").probe("pdom1").feature().create("ppb2", "PointExpr");
    model.component("comp1").probe("pdom1").feature("ppb2").set("probename", "ct_mp");
    model.component("comp1").probe("pdom1").feature("ppb2").set("expr", "ct");

    model.param().set("k_D_ctrl_temp_param", "k_D_ctrl");
    model.param().remove("k_D_ctrl_temp_param");

    model.component().create("comp2", true);

    model.component("comp2").physics().create("ge1", "GlobalEquations");
    model.component("comp2").physics("ge1").feature("ge1").setIndex("name", "I", 0, 0);
    model.component("comp2").physics("ge1").feature("ge1")
         .setIndex("equation", "It[s]-(c_set[1/(mol/m^3)]-comp1.c_mp[1/(mol/m^3)])", 0, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("description", "Time integral term", 0, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("name", "I2", 1, 0);
    model.component("comp2").physics("ge1").feature("ge1")
         .setIndex("equation", "I2t[s]+((u_temp-10)*(u_temp>10)+(u_temp-0)*(u_temp<0))[1/((m^4/(s*mol))*(mol/m^3))]", 1, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("description", "Time integral term 2", 1, 0);

    model.component("comp2").variable().create("var1");

    model.param().set("k_D_ctrl_temp_param", "k_D_ctrl");
    model.param().remove("k_D_ctrl_temp_param");

    model.component("comp2").physics("ge1").feature("ge1").setIndex("name", "d", 2, 0);
    model.component("comp2").physics("ge1").feature("ge1")
         .setIndex("equation", "(1[s])[1/s]*dt[s]+d+(k_D_ctrl*d(comp1.c_mp,TIME))[1/((m^4/(s*mol))*(mol/m^3))]", 2, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("initialValueU", 0, 2, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("initialValueUt", 0, 2, 0);
    model.component("comp2").physics("ge1").feature("ge1").setIndex("description", "Derivative term", 2, 0);

    model.param().set("k_D_ctrl_temp_param", "k_D_ctrl");
    model.param().remove("k_D_ctrl_temp_param");

    model.component("comp2").variable("var1")
         .set("u_temp", "nojac(0+k_P_ctrl*(c_set-comp1.c_mp)+k_I_ctrl*I[s][mol/m^3]-d[(m^4/(s*mol))*(mol/m^3)]+1/(1[s])*I2[s][(m^4/(s*mol))*(mol/m^3)])", "Temp control variable");
    model.component("comp2").variable("var1")
         .set("u_in_ctrl", "if(u_temp<0, 0, if(u_temp>10, 10, u_temp))", "Control variable");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1.2[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"3e-5[Pa*s]"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "comp2.u_in_ctrl");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(7);
    model.component("comp1").physics("spf").feature("inl2").set("U0in", "v_in_top");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(13);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(2, 3, 6, 8);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c00", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c_in_inlet", 0);
    model.component("comp1").physics("tds").create("in2", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in2").selection().set(7);
    model.component("comp1").physics("tds").feature("in2").setIndex("c0", "c_in_top", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(13);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "v_in_top", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "v_in_top", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "k_P_ctrl", 0);
    model.study("std1").feature("param").setIndex("plistarr", "-0.1 -0.5", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.05,1) range(1.1,0.1,6)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethod", "genalpha");
    model.sol("sol1").feature("t1").set("tstepsgenalpha", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.component("comp1").probe("pdom1").genResult("none");

    model.batch("p1").run("compute");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 71, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 71, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 71, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").label("\u6d53\u5ea6 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"comp2.I", "comp2.I2", "comp2.d"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"Time integral term", "Time integral term 2", "Derivative term"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("expr", new String[]{"comp2.I", "comp2.I2", "comp2.d"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"Time integral term", "Time integral term 2", "Derivative term"});
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().remove("arws1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg4").feature("str1").set("posmethod", "magnitude");
    model.result("pg4").feature("str1").set("mdist", new double[]{0.005, 0.1});
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5165\u53e3\u901f\u5ea6");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5165\u53e3\u901f\u5ea6");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "u<sub>in,ctrl</sub> (mm/s)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"comp2.u_in_ctrl"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"Control variable"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m/s"});
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6d53\u5ea6\uff0c\u6d4b\u91cf\u70b9");
    model.result("pg6").set("title", "\u6d53\u5ea6\uff0c\u6d4b\u91cf\u70b9");
    model.result("pg6").set("ylabel", "c<sub>mp</sub> (mol/m<sup>3</sup>)");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"c_mp"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u57df\u70b9\u63a2\u9488 1, c"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg6").run();

    model.title("\u4f7f\u7528 PID \u63a7\u5236\u5668\u7684\u8fc7\u7a0b\u63a7\u5236");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5b9e\u73b0 PID\uff08\u6bd4\u4f8b-\u79ef\u5206-\u5fae\u5206\uff09\u63a7\u5236\u7b97\u6cd5\u6765\u6a21\u62df\u8fc7\u7a0b\u63a7\u5236\u7cfb\u7edf\uff0c\u5e76\u786e\u5b9a\u6700\u4f73\u7684 PID \u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("pid_control.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
