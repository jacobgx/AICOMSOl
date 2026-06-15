/*
 * rotor_whirl.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:48 by COMSOL 6.3.0.290. */
public class rotor_whirl {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.component("comp1").multiphysics().create("brbc1", "BeamRotorBearingCoupling", 2);
    model.component("comp1").multiphysics("brbc1").set("BeamRotor_physics", "rotbm");
    model.component("comp1").multiphysics("brbc1").set("Bearing_physics", "hdb");
    model.component("comp1").multiphysics("brbc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rotbm", true);
    model.study("std1").feature("time").setSolveFor("/physics/hdb", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/brbc1", true);

    model.param().set("L", "1.3[m]");
    model.param().descr("L", "\u8f6c\u5b50\u957f\u5ea6");
    model.param().set("D", "0.1[m]");
    model.param().descr("D", "\u8f6c\u5b50\u76f4\u5f84");
    model.param().set("E0", "2.05E11[Pa]");
    model.param().descr("E0", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("rho0", "7800[kg/m^3]");
    model.param().descr("rho0", "\u5bc6\u5ea6");
    model.param().set("nu0", "0.3");
    model.param().descr("nu0", "\u6cca\u677e\u6bd4");
    model.param().set("Lj", "0.025[m]");
    model.param().descr("Lj", "\u8f74\u627f\u957f\u5ea6");
    model.param().set("C", "5e-5[m]");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("mu0", "0.072[Pa*s]");
    model.param().descr("mu0", "\u9ecf\u5ea6");
    model.param().set("Ow", "9000[rpm]");
    model.param().descr("Ow", "\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 L");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "D/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lj");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"L-Lj", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().all();
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", new String[]{"E0"});

    model.component("comp1").physics("rotbm").selection().set(6);
    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "Ow");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "D");
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);
    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure", "mu0");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").label("\u6881\u8f6c\u5b50");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 150);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").label("\u8f74\u627f");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(1, 2, 4, 7, 14, 15, 17, 19);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(8, 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5e-4,0.2)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_pfilm").set("scaleval", "1.0e5");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 401, 0);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (rotbm)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "pfilm");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "pfilm");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("inherittubescale", false);
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises \u5e94\u529b (N/m<sup>2</sup>)");
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2, 3, 4);
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u538b\u529b (Pa)");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8f68\u9053");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(3);
    model.result("pg3").feature("ptgr1").set("expr", "w/C");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "v/C");
    model.result("pg3").feature("ptgr1").set("linecolor", "magenta");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u5de6\u8f74\u627f\u8f74\u9888\u7684\u8f68\u9053");
    model.result("pg3").set("preserveaspect", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u52a0\u901f\u5ea6\u4e0e\u65f6\u95f4\u7684\u5173\u7cfb");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").label("y \u52a0\u901f\u5ea6");
    model.result("pg4").feature("ptgr1").selection().set(3);
    model.result("pg4").feature("ptgr1").set("expr", "vtt");
    model.result("pg4").feature("ptgr1").set("titletype", "manual");
    model.result("pg4").feature("ptgr1").set("linewidth", 3);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").feature("ptgr1").set("autosolution", false);
    model.result("pg4").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").label("z \u52a0\u901f\u5ea6");
    model.result("pg4").feature("ptgr2").set("expr", "wtt");
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u52a0\u901f\u5ea6 (m/s<sup>2</sup>)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u52a0\u901f\u5ea6\u8c31");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("xdata", "fourier");
    model.result("pg5").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg5").feature("ptgr1").set("scale", "dividenfreq");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("xdata", "fourier");
    model.result("pg5").feature("ptgr2").set("fouriershow", "spectrum");
    model.result("pg5").feature("ptgr2").set("scale", "dividenfreq");
    model.result("pg5").run();
    model.result("pg3").run();

    model.title("\u5747\u5300\u8f74\u5728\u91cd\u529b\u4f5c\u7528\u4e0b\u7684\u6da1\u52a8");

    model
         .description("\u201c\u5747\u5300\u8f74\u7684\u6da1\u52a8\u201d\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5bf9\u91cd\u529b\u4f5c\u7528\u4e0b\u7684\u5747\u5300\u8f74\u6267\u884c\u77ac\u6001\u5206\u6790\u3002\u8f74\u7684\u4e24\u7aef\u7531\u4e24\u4e2a\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u652f\u6491\u3002\u8f6c\u5b50\u56e0\u9640\u87ba\u6548\u5e94\u800c\u7ed5\u5176\u521d\u59cb\u8f74\u6da1\u52a8\uff0c\u5e76\u6700\u7ec8\u5230\u8fbe\u7a33\u5b9a\u8f68\u9053\u3002\n\n\u7ed3\u679c\u5305\u542b\u8f6c\u5b50\u627f\u53d7\u6700\u5927\u5f2f\u66f2\u5e94\u529b\u65f6\u7684\u5e94\u529b\u5206\u5e03\u3001\u8f74\u627f\u6d41\u4f53\u538b\u529b\u548c\u8f74\u9888\u8f68\u9053\u3002\u5728\u6700\u540e\u7684\u7ed3\u679c\u4e2d\uff0c\u8f74\u9888\u5411\u5916\u505a\u87ba\u65cb\u8fd0\u52a8\uff0c\u5e76\u6700\u7ec8\u5230\u8fbe\u7a33\u5b9a\u8f68\u9053\u3002\n\n\u6b64\u5916\uff0c\u672c\u4f8b\u8fd8\u5206\u6790\u4e86\u8f74\u9888\u52a0\u901f\u5ea6\u7684\u9891\u8c31\uff0c\u8bc1\u5b9e\u4e86\u534a\u9891\u6da1\u52a8\u662f\u6da1\u52a8\u7684\u4e3b\u8981\u6a21\u5f0f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("rotor_whirl.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
