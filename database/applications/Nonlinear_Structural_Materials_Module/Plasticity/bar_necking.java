/*
 * bar_necking.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:33 by COMSOL 6.3.0.290. */
public class bar_necking {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("sigma0", "450[MPa]");
    model.param().descr("sigma0", "\u521d\u59cb\u5c48\u670d\u5e94\u529b");
    model.param().set("sigmaSF", "715[MPa]");
    model.param().descr("sigmaSF", "\u9971\u548c\u6d41\u52a8\u5e94\u529b");
    model.param().set("H", "129.24[MPa]");
    model.param().descr("H", "\u7ebf\u6027\u786c\u5316\u7cfb\u6570");
    model.param().set("zeta", "16.93");
    model.param().descr("zeta", "\u9971\u548c\u6307\u6570");
    model.param().set("delta", "0[m]");
    model.param().descr("delta", "\u9876\u90e8\u4f4d\u79fb");
    model.param().set("H0", "53.334[mm]");
    model.param().descr("H0", "\u6837\u6761\u957f\u5ea6");
    model.param().set("R0", "6.413[mm]");
    model.param().descr("R0", "\u6837\u6761\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").label("\u6307\u5b9a\u4f4d\u79fb\uff0c\u5e95\u90e8");
    model.component("comp1").physics("solid").feature("disp1").selection().set(2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").label("\u6307\u5b9a\u4f4d\u79fb\uff0c\u9876\u90e8");
    model.component("comp1").physics("solid").feature("disp2").selection().set(3);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "delta", 2);
    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "totalLagrangian");
    model.component("comp1").physics("solid").feature("lemm1").set("strainDecomposition", "multiplicative");
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("nonlocalPlasticModel", "impGradient");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("lint", "0.15[mm]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"206.9[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.29"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"sigma0"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("an1")
         .set("funcname", "sig_h");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("an1").set("args", "epe");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("an1")
         .set("expr", "H*epe+(sigmaSF-sigma0)*(1-exp(-zeta*epe))");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("an1")
         .setIndex("argunit", 1, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("an1").set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"sig_h(epe)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "ddog");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "sigma0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "sigma0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "delta", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0, 0.25, 10)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u9888\u534a\u5f84");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(3);
    model.result("pg4").feature("ptgr1").set("expr", "u+R0");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "delta");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("descractive", true);
    model.result("pg4").feature("ptgr1").set("descr", "\u9888\u534a\u5f84");
    model.result("pg4").feature("ptgr1").set("titletype", "none");
    model.result("pg4").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(3);
    model.result().numerical("int1").set("expr", new String[]{"solid.RFz"});
    model.result().numerical("int1").set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cz \u5206\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"N"});
    model.result().numerical("int1").setIndex("unit", "kN", 0);
    model.result().numerical("int1").set("intsurface", false);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u53cd\u4f5c\u7528\u529b");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9876\u90e8\u4f4d\u79fb (mm)");
    model.result().numerical().create("max1", "MaxSurface");
    model.result().numerical("max1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").set("expr", new String[]{"solid.epeGp"});
    model.result().numerical("max1").set("descr", new String[]{"\u7b49\u6548\u5851\u6027\u5e94\u53d8"});
    model.result().numerical("max1").set("unit", new String[]{"1"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u6700\u5927\u503c 1");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").setIndex("looplevel", 33, 0);
    model.result("pg2").set("edges", false);
    model.result("pg2").run();

    model.title("\u5f39\u5851\u6027\u91d1\u5c5e\u6761\u7684\u9888\u7f29");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u4e00\u79cd\u91c7\u7528\u975e\u7ebf\u6027\u5404\u5411\u540c\u6027\u786c\u5316\u7684\u5f39\u5851\u6027\u6750\u6599\u5236\u6210\u7684\u5706\u5f62\u91d1\u5c5e\u6761\u5728\u5355\u8f74\u62c9\u4f38\u6761\u4ef6\u4e0b\u7684\u884c\u4e3a\uff0c\u6355\u6349\u4e86\u9888\u7f29\u73b0\u8c61\u5e76\u6a21\u62df\u5176\u751f\u957f\u8fc7\u7a0b\u3002\u8fd9\u662f\u4e00\u4e2a\u5927\u5e94\u53d8\u5851\u6027\u7684\u7ecf\u5178\u57fa\u51c6\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("bar_necking.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
