/*
 * turbulent_backstep.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class turbulent_backstep {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("S", "0.0381[m]");
    model.param().descr("S", "\u53f0\u9636\u9ad8\u5ea6");
    model.param().set("hc", "0.0762[m]");
    model.param().descr("hc", "\u5165\u53e3\u901a\u9053\u9ad8\u5ea6");
    model.param().set("H", "0.1143[m]");
    model.param().descr("H", "\u51fa\u53e3\u901a\u9053\u9ad8\u5ea6");
    model.param().set("L1", "0.3048[m]");
    model.param().descr("L1", "\u5165\u53e3\u901a\u9053\u957f\u5ea6");
    model.param().set("L2", "1.3335[m]");
    model.param().descr("L2", "\u51fa\u53e3\u901a\u9053\u957f\u5ea6");
    model.param().set("rhof", "1.23[kg/m^3]");
    model.param().descr("rhof", "\u5bc6\u5ea6");
    model.param().set("muf", "1.79e-5[Pa*s]");
    model.param().descr("muf", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("Re", "47625");
    model.param().descr("Re", "\u96f7\u8bfa\u6570");
    model.param().set("Uav", "Re*muf/rhof/S");
    model.param().descr("Uav", "\u5e73\u5747\u5165\u53e3\u6d41\u901f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L1+L2", "hc"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-L1", "S"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L2", "S"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 6);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhof"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"muf"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uav");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(6);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hgrad", 1.03);
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", "5e-4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
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
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(2, 3, 4, 5);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg3").feature("line1").feature().create("hght1", "Height");
    model.result("pg3").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg3").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.005);
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg1").feature("str1").set("arrowcountactive", true);
    model.result("pg1").feature("str1").set("arrowcount", 150);
    model.result("pg1").feature("str1").set("arrowscaleactive", true);
    model.result("pg1").feature("str1").set("arrowscale", "0.0008");
    model.result("pg1").run();

    model.view().create("view3", 2);
    model.view("view3").set("locked", false);
    model.view("view3").axis().set("xmin", -0.034643180668354034);
    model.view("view3").axis().set("xmax", 0.3820282518863678);
    model.view("view3").axis().set("ymin", -0.1060839593410492);
    model.view("view3").axis().set("ymax", 0.2271644026041031);
    model.view("view3").axis().set("viewscaletype", "manual");

    model.result("pg1").run();
    model.result("pg1").set("view", "view3");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "u");
    model.result("pg4").feature("con1").set("descr", "\u901f\u5ea6\u573a\uff0cx \u5206\u91cf");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("con1").feature("col1").set("expr", "x/S");
    model.result("pg4").run();
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").label("\u56de\u6d41\u957f\u5ea6");
    model.result("pg4").run();

    model.title("\u540e\u53f0\u9636\u6e4d\u6d41");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u96f7\u8bfa\u6570\u7ea6\u4e3a Re=48,000 \u65f6\uff0c\u540e\u53f0\u9636\u51e0\u4f55\u4e0a\u7684\u6e4d\u6d41\u3002\u8be5\u6a21\u578b\u4e13\u7528\u4e8e\u7814\u7a76\u56de\u6d41\u57fa\u51c6\u95ee\u9898\uff0c\u901a\u8fc7 k-\u03b5 \u6e4d\u6d41\u6a21\u578b\u8fdb\u884c\u6c42\u89e3\uff0c\u5e76\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("turbulent_backstep.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
