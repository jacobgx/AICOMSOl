/*
 * pore_scale_flow_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:29 by COMSOL 6.3.0.290. */
public class pore_scale_flow_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "pore_scale_flow_3d.stl");
    model.component("comp1").mesh("mesh1").feature("imp1").set("createdom", true);
    model.component("comp1").mesh("mesh1").feature("imp1").set("facepartition", "detectfaces");
    model.component("comp1").mesh("mesh1").feature("imp1").set("stltoltype", "absolute");
    model.component("comp1").mesh("mesh1").feature("imp1").set("stltolabs", "1e-5");
    model.component("comp1").mesh("mesh1").feature("imp1").set("facemaxangle", 62);
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").view("view1").set("rendermesh", true);

    model.component("comp1").mesh("mesh1").create("join1", "JoinEntities");
    model.component("comp1").mesh("mesh1").feature("join1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("join1").selection().set(14, 15, 16, 17, 18, 19, 20);
    model.component("comp1").mesh("mesh1").run("join1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("narrowreg", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(14, 36, 37);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.04[cm]");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(14, 36, 37);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 10);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7f51\u683c\u56fe 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").run();

    model.param().set("rho_f", "1000[kg/m^3]");
    model.param().descr("rho_f", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu_f", "1e-3[Pa*s]");
    model.param().descr("mu_f", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("u_in", "1e-4[m/s]");
    model.param().descr("u_in", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("width", "2[cm]");
    model.param().descr("width", "REV \u5bbd\u5ea6");
    model.param().set("length", "6[cm]");
    model.param().descr("length", "REV \u957f\u5ea6");
    model.param().set("V_tot", "width^2*length");
    model.param().descr("V_tot", "\u603b Rev \u4f53\u79ef");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.material().create("mat1", "Common", "");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "u_in");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").set(2);

    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(16, 21);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").set(16, 21);

    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u58c1");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(4, 10, 30);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").all();
    model.component("comp1").selection("sel4").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5bf9\u79f0");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel4"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1", "sel2", "sel3"});

    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("dif1");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("sel3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "surf1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("color", "custom");
    model.result("pg2").feature("line1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").selection().named("sel1");
    model.result("pg2").feature("str1").set("selnumber", 60);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").set("tuberadiusscale", 0.01);
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("colortable", "AuroraBorealis");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u5165\u53e3\u5e73\u5747\u503c");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("\u51fa\u53e3\u5e73\u5747\u503c");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("por", "intop1(1)/V_tot");
    model.component("comp1").variable("var1").descr("por", "\u5b54\u9699\u7387");
    model.component("comp1").variable("var1").set("dPdL", "-(aveop2(p)-aveop1(p))/length");
    model.component("comp1").variable("var1").descr("dPdL", "\u538b\u964d");
    model.component("comp1").variable("var1").set("u_out", "spf.out1.massFlowRate/rho_f/width^2");
    model.component("comp1").variable("var1").descr("u_out", "\u8868\u89c2\u51fa\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("kappa", "u_out*mu_f/dPdL");
    model.component("comp1").variable("var1").descr("kappa", "\u6e17\u900f\u7387");

    model.sol("sol1").updateSolution();

    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"por"});
    model.result().numerical("gev1").set("descr", new String[]{"\u5b54\u9699\u7387"});
    model.result().numerical("gev1").set("expr", new String[]{"por", "kappa"});
    model.result().numerical("gev1").set("descr", new String[]{"\u5b54\u9699\u7387", "\u6e17\u900f\u7387"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("pointtype", "interactivearrow");
    model.result("pg2").feature("str1").set("localtimeshifts", 2000);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("sweeptype", "streamline");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export("anim1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("localtime", 51147.8582015585);

    model.title("\u5728\u5fae\u89c2\u5c3a\u5ea6\u4e0a\u5206\u6790\u591a\u5b54\u7ed3\u6784");

    model
         .description("\u7531\u4e8e\u5b9e\u9645\u591a\u5b54\u7ed3\u6784\u672c\u8eab\u7684\u590d\u6742\u6027\uff0c\u6211\u4eec\u5f88\u96be\u5bf9\u5176\u4e2d\u7684\u6d41\u52a8\u8fdb\u884c\u5efa\u6a21\u3002\u5728\u5b9e\u9645\u5e94\u7528\u4e2d\uff0c\u8be6\u7ec6\u89e3\u6790\u6d41\u573a\u662f\u4e0d\u53ef\u884c\u7684\u3002\u56e0\u6b64\uff0c\u9700\u8981\u91c7\u7528\u5b8f\u89c2\u65b9\u6cd5\u6765\u5229\u7528\u591a\u5b54\u7ed3\u6784\u7684\u5e73\u5747\u91cf\uff0c\u6bd4\u5982\u5b54\u9699\u7387\u548c\u6e17\u900f\u7387\u3002\u672c\u4f8b\u5bfc\u5165\u4e00\u4e2a\u591a\u5b54\u7ed3\u6784\u7684 STL \u6587\u4ef6\uff0c\u5e76\u8be6\u7ec6\u5206\u6790\u5b54\u9699\u5c3a\u5ea6\u4e0b\u7684\u6d41\u573a\u3002");

    model.sol("sol1").clearSolutionData();

    model.label("pore_scale_flow_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
