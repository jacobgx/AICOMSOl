/*
 * blasting_rock.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:06 by COMSOL 6.3.0.290. */
public class blasting_rock {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").view("view1").set("showgrid", false);

    model.param().set("H", "240[mm]");
    model.param().descr("H", "\u6df1\u5ea6");
    model.param().set("L", "H");
    model.param().descr("L", "\u5bbd\u5ea6");
    model.param().set("L1", "L/24");
    model.param().descr("L1", "\u8f7d\u8377\u6269\u5c55");
    model.param().set("Q", "1[g]");
    model.param().descr("Q", "\u70b8\u836f\u91cf");
    model.param().set("P0", "140e6[N]*(Q/1[kg])^(2/3)");
    model.param().descr("P0", "\u8f7d\u8377\u5927\u5c0f");
    model.param().set("t0", "0.81e-3[s]*(Q/1[kg])^(1/3)");
    model.param().descr("t0", "\u52a0\u8f7d\u65f6\u95f4");
    model.param().set("gamma", "1.86");
    model.param().descr("gamma", "\u8870\u51cf\u7387");
    model.param().set("u0", "50[um]");
    model.param().descr("u0", "\u4f4d\u79fb\u6bd4\u4f8b");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "Pb");
    model.component("comp1").func("pw1").set("arg", "t");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t0", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "P0*exp(-gamma*t/t0)*sin(4*pi/(1+t0/t))", 0, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "N");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "L", "H"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("E", "50[GPa]");
    model.component("comp1").physics("solid").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("nu", "2/7");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", 2700);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "0.25*Pb(t)/L1^2*(X<=L1)*(Y<=L1)"});
    model.component("comp1").physics("solid").create("lrb1", "LowReflectingBoundary", 2);
    model.component("comp1").physics("solid").feature("lrb1").selection().set(5, 6);
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3, 7, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "floor(L/L1)");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", "floor(L/L1)");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,2e-6,1.5e-4)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "u0");
    model.sol("sol1").feature("t1").set("rhoinf", 0.5);

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(2);
    model.result("pg1").feature("ptgr1").set("expr", "w");
    model.result("pg1").feature("ptgr1").set("unit", "um");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", "solid.sz");
    model.result("pg2").feature("vol1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 26, 0);

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg2").run();
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("expr", "w");
    model.result("pg2").feature("iso1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").set("tlist", "range(0,2e-6,1.5e-4)");
    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid/lrb1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "u0");
    model.sol("sol2").feature("t1").set("rhoinf", 0.5);

    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("data", "dset2");
    model.result("pg1").feature("ptgr2").set("linestyle", "dotted");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").create("ptgr3", "PointGraph");
    model.result("pg1").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr3").set("linewidth", "preference");
    model.result("pg1").feature("ptgr3").set("expr", "(t/t0-1)*40");
    model.result("pg1").feature("ptgr3").set("xdata", "expr");
    model.result("pg1").feature("ptgr3").set("xdataexpr", "L/solid.cp");
    model.result("pg1").feature("ptgr3").selection().set(2);
    model.result("pg1").feature("ptgr3").set("linestyle", "dashed");
    model.result("pg1").feature("ptgr3").set("linecolor", "red");
    model.result("pg1").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr4").set("xdataexpr", "L*sqrt(5)/solid.cp");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5782\u76f4\u4f4d\u79fb (um)");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "xz");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "mir2");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", "H");
    model.result().dataset().create("sfft1", "SpatialFFT");
    model.result().dataset("sfft1").set("data", "cpl1");
    model.result().dataset("sfft1").set("gridres", "manual");
    model.result().dataset("sfft1").set("sampresx", 20);
    model.result().dataset("sfft1").set("sampresy", 20);
    model.result().dataset("sfft1").set("layout", "padding");
    model.result().dataset("sfft1").set("padx", 40);
    model.result().dataset("sfft1").set("pady", 40);
    model.result().dataset().duplicate("mir3", "mir1");
    model.result().dataset().duplicate("mir4", "mir2");
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset().duplicate("sfft2", "sfft1");
    model.result().dataset("mir3").set("data", "dset2");
    model.result().dataset("mir4").set("data", "mir3");
    model.result().dataset("cpl2").set("data", "mir4");
    model.result().dataset("sfft2").set("data", "cpl2");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "sfft1");
    model.result("pg3").setIndex("looplevel", 36, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").feature("surf1").set("expr", "abs(fft(w))");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "sfft2");
    model.result("pg3").feature("surf2").setIndex("looplevel", 36, 0);
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 61, 0);
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").setIndex("looplevel", 61, 0);
    model.result("pg4").run();

    model.title("\u5ca9\u5c42\u4e2d\u7684\u7206\u70b8\u6ce2\u4f20\u64ad");

    model
         .description("\u672c\u4f8b\u8fdb\u884c\u77ac\u6001\u5206\u6790\uff0c\u6a21\u62df\u4e86\u5ca9\u5c42\u8868\u9762\u77ed\u6682\u7684\u7206\u70b8\u8f7d\u8377\u4ea7\u751f\u7684\u5ca9\u5c42\u4e2d\u7684\u6ce2\u4f20\u64ad\uff0c\u8fd9\u79cd\u8f7d\u8377\u5e38\u89c1\u4e8e\u96a7\u9053\u5f00\u6316\u53ca\u5176\u4ed6\u4f7f\u7528\u7206\u7834\u7684\u5f00\u6316\u8fc7\u7a0b\u3002\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4f4e\u53cd\u5c04\u8fb9\u754c\u6761\u4ef6\u6765\u5c06\u6c42\u89e3\u57df\u622a\u65ad\u5230\u4e00\u4e2a\u5408\u7406\u7684\u5c3a\u5bf8\u3002\u7ed3\u679c\u4e0e\u5df2\u53d1\u5e03\u7684\u7814\u7a76\u76f8\u5f53\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("blasting_rock.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
