/*
 * expansion_fan.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class expansion_fan {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\High_Mach_Number_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("hmnf", "HighMachNumberFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/hmnf", true);

    model.param().set("M1", "2.5");
    model.param().descr("M1", "\u5165\u53e3\u9a6c\u8d6b\u6570");
    model.param().set("pin_tot", "12[psi]");
    model.param().descr("pin_tot", "\u5165\u53e3\u603b\u538b");
    model.param().set("Tin_tot", "550[R]");
    model.param().descr("Tin_tot", "\u5165\u53e3\u603b\u6e29\u5ea6");
    model.param().set("Rs", "287[J/kg/K]");
    model.param().descr("Rs", "\u6bd4\u6c14\u4f53\u5e38\u6570");
    model.param().set("gamma", "1.4[1]");
    model.param().descr("gamma", "\u6bd4\u70ed\u7387");
    model.param().set("L_in", "3[m]");
    model.param().descr("L_in", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("L_wave", "4.5[m]");
    model.param().descr("L_wave", "\u62d0\u89d2\u540e\u7684\u957f\u5ea6");
    model.param().set("ht", "L_in");
    model.param().descr("ht", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("R_fillet", "0.1[m]");
    model.param().descr("R_fillet", "\u5706\u89d2\u534a\u5f84");
    model.param().set("theta", "15[deg]");
    model.param().descr("theta", "\u6d41\u52a8\u504f\u8f6c\u89d2");
    model.param().set("u_in", "M1*sqrt(gamma*Rs*Tin_tot/(1+0.5*M1^2*(-1+gamma))+eps)");
    model.param().descr("u_in", "\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("x", "0, L_in, L_in, L_in+L_wave, L_in+L_wave, L_in+L_wave, L_in+L_wave, 0, 0, 0");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("y", "0, 0, 0, -L_wave*tan(theta), -L_wave*tan(theta), ht, ht, ht, ht, 0");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("pol1", 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "R_fillet");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("v1", "sqrt((gamma+1)/(gamma-1))*atan(sqrt((gamma-1)*(M1^2-1)/(gamma+1)))-atan(sqrt(M1^2-1))");
    model.component("comp1").variable("var1").descr("v1", "\u5165\u53e3\u666e\u6717\u7279-\u8fc8\u8036\u51fd\u6570");
    model.component("comp1").variable("var1")
         .set("vi2", "sqrt((gamma+1)/(gamma-1))*atan(sqrt((gamma-1)*(M2^2-1)/(gamma+1)))-atan(sqrt(M2^2-1))");
    model.component("comp1").variable("var1")
         .descr("vi2", "\u81a8\u80c0\u6ce2\u540e\u7684\u666e\u6717\u7279-\u8fc8\u8036\u51fd\u6570\u731c\u6d4b\u503c");
    model.component("comp1").variable("var1").set("v2", "theta+v1");
    model.component("comp1").variable("var1").descr("v2", "\u5168\u5c40\u65b9\u7a0b\u7684\u6b8b\u5dee");
    model.component("comp1").variable("var1").set("Tin_stat", "Tin_tot/(1+0.5*M1^2*(-1+gamma))");
    model.component("comp1").variable("var1").descr("Tin_stat", "\u5165\u53e3\u9759\u6001\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("pin_stat", "pin_tot/(1+0.5*M1^2*(-1+gamma))^(gamma/(-1+gamma))");
    model.component("comp1").variable("var1").descr("pin_stat", "\u5165\u53e3\u9759\u538b");

    model.component("comp1").physics("hmnf").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1")
         .set("k", new String[]{"1e-8", "0", "0", "0", "1e-8", "0", "0", "0", "1e-8"});
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs", "Rs");
    model.component("comp1").physics("hmnf").feature("fluid1").set("CpOrGammaOption", "gamma");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma", "gamma");
    model.component("comp1").physics("hmnf").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("mu", "1e-8");
    model.component("comp1").physics("hmnf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("hmnf").feature("init1").set("u_init", new String[]{"u_in", "0", "0"});
    model.component("comp1").physics("hmnf").feature("init1").set("p_init", "pin_stat");
    model.component("comp1").physics("hmnf").feature("init1").set("Tinit", "Tin_stat");
    model.component("comp1").physics("hmnf").create("hminl1", "HighMachNumberFlowInlet", 1);
    model.component("comp1").physics("hmnf").feature("hminl1").selection().set(1);
    model.component("comp1").physics("hmnf").feature("hminl1").set("FlowCondition", "Supersonic");
    model.component("comp1").physics("hmnf").feature("hminl1").set("InputState", "TotalConditions");
    model.component("comp1").physics("hmnf").feature("hminl1").set("p0tot", "pin_tot");
    model.component("comp1").physics("hmnf").feature("hminl1").set("T0tot", "Tin_tot");
    model.component("comp1").physics("hmnf").feature("hminl1").set("Ma0", "M1");
    model.component("comp1").physics("hmnf").create("hmout1", "HighMachNumberFlowOutlet", 1);
    model.component("comp1").physics("hmnf").feature("hmout1").selection().set(5);
    model.component("comp1").physics("hmnf").feature("hmout1").set("FlowCondition", "Supersonic");
    model.component("comp1").physics("hmnf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("hmnf").feature("ge1").setIndex("name", "M2", 0, 0);
    model.component("comp1").physics("hmnf").feature("ge1").setIndex("equation", "v2-vi2", 0, 0);
    model.component("comp1").physics("hmnf").feature("ge1").setIndex("initialValueU", "M1", 0, 0);
    model.component("comp1").physics("hmnf").feature("ge1")
         .setIndex("description", "\u81a8\u80c0\u6ce2\u540e\u7684\u9a6c\u8d6b\u6d41\uff0c\u89e3\u6790\u89e3", 0, 0);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("errestandadap", "adaption");
    model.study("std1").feature("stat").set("meshadaptmethod", "modify");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (hmnf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (hmnf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (hmnf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u9a6c\u8d6b\u6570 (hmnf)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "hmnf.Ma");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").selection().set(1);
    model.result("pg4").feature("str1").set("selnumber", 10);
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg4").feature("str1").set("arrowscaleactive", true);
    model.result("pg4").feature("str1").set("arrowscale", "1e-3");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", "L_in+L_wave");
    model.result().dataset("cpt1").set("pointy", "-L_wave*tan(theta)/2");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u9a6c\u8d6b\u6570");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev1").setIndex("expr", "hmnf.Ma", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u9a6c\u8d6b\u6570");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").label("\u603b\u538b");
    model.result().numerical("pev2").set("data", "cpt1");
    model.result().numerical("pev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev2").setIndex("expr", "p*(1+0.5*hmnf.Ma^2*(-1+gamma))^(gamma/(-1+gamma))", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u603b\u538b");
    model.result().numerical("pev2").set("table", "tbl2");
    model.result().numerical("pev2").setResult();
    model.result().numerical().create("pev3", "EvalPoint");
    model.result().numerical("pev3").label("\u603b\u6e29\u5ea6");
    model.result().numerical("pev3").set("data", "cpt1");
    model.result().numerical("pev3").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev3").setIndex("expr", "T*(1+0.5*hmnf.Ma^2*(-1+gamma))", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u603b\u6e29\u5ea6");
    model.result().numerical("pev3").set("table", "tbl3");
    model.result().numerical("pev3").setResult();
    model.result().numerical().create("pev4", "EvalPoint");
    model.result().numerical("pev4").label("\u603b\u5bc6\u5ea6");
    model.result().numerical("pev4").set("data", "cpt1");
    model.result().numerical("pev4").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev4").setIndex("expr", "hmnf.rho*(1+0.5*hmnf.Ma^2*(-1+gamma))^(1/(-1+gamma))", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u603b\u5bc6\u5ea6");
    model.result().numerical("pev4").set("table", "tbl4");
    model.result().numerical("pev4").setResult();
    model.result().numerical().create("pev5", "EvalPoint");
    model.result().numerical("pev5").label("\u9a6c\u8d6b\u6570\uff0c\u89e3\u6790\u89e3");
    model.result().numerical("pev5").set("data", "cpt1");
    model.result().numerical("pev5").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev5").setIndex("expr", "M2", 0);
    model.result().numerical("pev5").setIndex("descr", "", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u9a6c\u8d6b\u6570\uff0c\u89e3\u6790\u89e3");
    model.result().numerical("pev5").set("table", "tbl5");
    model.result().numerical("pev5").setResult();
    model.result("pg4").run();

    model.title("\u81a8\u80c0\u6ce2");

    model
         .description("\u8d85\u97f3\u901f\u6d41\u52a8\u7684\u4e00\u4e2a\u91cd\u8981\u4e14\u6709\u8da3\u7684\u73b0\u8c61\u662f\u81a8\u80c0\u6ce2\uff0c\u6d41\u4f53\u9047\u5230\u51f8\u8d77\u6216\u81a8\u80c0\u62d0\u89d2\u65f6\u4f1a\u53d1\u751f\u8fd9\u79cd\u73b0\u8c61\u3002\u6d41\u52a8\u7684\u65b9\u5411\u5728\u81a8\u80c0\u6ce2\u5185\u5e73\u6ed1\u5730\u53d8\u5316\uff0c\u540c\u65f6\u9a6c\u8d6b\u6570\u589e\u5927\u3002\n\n\u6b64\u4e8c\u7ef4\u793a\u4f8b\u6a21\u62df\u975e\u9ecf\u6027\u6d41\u52a8\u60c5\u51b5\u4e0b\u7684\u81a8\u80c0\u6ce2\uff0c\u4eff\u771f\u7ed3\u679c\u4e0e\u975e\u9ecf\u6027\u53ef\u538b\u7f29\u6d41\u52a8\u7406\u8bba\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002\u4e0e\u5efa\u6a21\u533a\u57df\u7684\u5176\u4f59\u90e8\u5206\u76f8\u6bd4\uff0c\u81ea\u9002\u5e94\u7f51\u683c\u7528\u4e8e\u66f4\u7cbe\u7ec6\u5730\u89e3\u6790\u81a8\u80c0\u6ce2\u533a\u57df\u3002\n\n\u7ed3\u679c\u4e0e\u4f7f\u7528\u65e0\u9ecf\u6027\u53ef\u538b\u7f29\u6d41\u52a8\u7406\u8bba\u6240\u5f97\u7684\u7ed3\u679c\u76f8\u5f53\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("expansion_fan.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
