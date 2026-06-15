/*
 * micropump_mechanism.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:57 by COMSOL 6.3.0.290. */
public class micropump_mechanism {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").field("velocity").field("u_fluid");
    model.component("comp1").physics("spf").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u_solid");
    model.component("comp1").physics("solid").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 1);
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("fsi1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/fsi1", true);

    model.param().set("Re", "16");
    model.param().descr("Re", "\u96f7\u8bfa\u6570");
    model.param().set("coeff", "4/sqrt(Re)");
    model.param().descr("coeff", "\u7528\u4e8e\u66f4\u6539\u96f7\u8bfa\u6570\u7684\u7cfb\u6570");
    model.param().set("dens", "1000[kg/m^3]");
    model.param().descr("dens", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("visc", "0.001[Pa*s]*coeff");
    model.param().descr("visc", "\u6d41\u4f53\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("U", "16[cm/s]/coeff");
    model.param().descr("U", "\u5e73\u5747\u5165\u53e3\u6d41\u901f");
    model.param().set("H", "100[um]");
    model.param().descr("H", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("W", "10[um]");
    model.param().descr("W", "\u57df\u5bbd\u5ea6");
    model.param().set("rp", "2[um]");
    model.param().descr("rp", "\u9600\u74e3\u534a\u5f84");
    model.param().set("hp", "70[um]");
    model.param().descr("hp", "\u9600\u74e3\u9ad8\u5ea6");
    model.param().set("L", "600[um]");
    model.param().descr("L", "\u901a\u9053\u957f\u5ea6");
    model.param().set("beta", "45[deg]");
    model.param().descr("beta", "\u9600\u74e3\u503e\u89d2");
    model.param().set("x0", "150[um]");
    model.param().descr("x0", "\u9600\u74e3\u4e2d\u5fc3\u4f4d\u7f6e");
    model.param().set("Re_check", "dens*U*H/(visc)");
    model.param().descr("Re_check", "\u96f7\u8bfa\u6570");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-L/2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"2*rp", "2*hp"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"x0-hp*sin(beta)/2", "0"});
    model.component("comp1").geom("geom1").feature("r2").set("rot", "-beta");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("r1");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("int1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("int1", 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "rp");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("copy2").set("displx", "-2*x0");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"0.8*H", "H"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-0.4*H", "H"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intopL");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intopR");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(17);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("UoutL", "-intopL(u_fluid)*W");
    model.component("comp1").variable("var1").descr("UoutL", "\u5de6\u4fa7\u51fa\u53e3\u7684\u6d41\u7387");
    model.component("comp1").variable("var1").set("UoutR", "intopR(u_fluid)*W");
    model.component("comp1").variable("var1").descr("UoutR", "\u53f3\u4fa7\u51fa\u53e3\u7684\u6d41\u7387");
    model.component("comp1").variable("var1").set("UoutNet", "UoutR-UoutL");
    model.component("comp1").variable("var1").descr("UoutNet", "\u51c0\u6d41\u7387");

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "Vpump", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "Vpumpt-UoutNet", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "\u4ece\u5de6\u5411\u53f3\u6cf5\u9001\u7684\u51c0\u4f53\u79ef", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "m^3", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "m^3/s", 0, 0);

    model.component("comp1").common("free1").set("stiffeningFactor", "100");
    model.component("comp1").common("free1").selection().set(1);
    model.component("comp1").common().create("fix1", "FixedBoundary");
    model.component("comp1").common("fix1").selection().set(1, 2, 7, 9, 16, 17);
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacement");
    model.component("comp1").common("pnmd1").selection().set(3, 12);

    model.component("comp1").physics("spf").selection().set(1, 3);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(10);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U*6*s*(1-s)*sin(2*pi*t/(1[s]))");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(1, 17);
    model.component("comp1").physics("spf").feature("out1").set("SuppressBackflow", false);
    model.component("comp1").physics("solid").selection().set(1, 2, 4);
    model.component("comp1").physics("solid").feature("lemm1").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(4, 13);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u56fa\u4f53");
    model.component("comp1").material("mat2").selection().set(2, 4);
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"dens"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"visc"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"3.6e5"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.499"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"970"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 10);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 1.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.6);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.7);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.02,2)");
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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"Vpump"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u4ece\u5de6\u5411\u53f3\u6cf5\u9001\u7684\u51c0\u4f53\u79ef"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("expr", new String[]{"Vpump"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u4ece\u5de6\u5411\u53f3\u6cf5\u9001\u7684\u51c0\u4f53\u79ef"});
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").label("\u52a8\u7f51\u683c");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "surface");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection().set(1, 2, 4);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result("pg1").run();
    model.result("pg1").label("\u6d41\u52a8\u548c\u5e94\u529b");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "solid.misesGp");
    model.result("pg1").feature("surf2").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf2").set("colortable", "Traffic");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg4").label("\u4ece\u5de6\u5411\u53f3\u6cf5\u9001\u7684\u51c0\u4f53\u79ef");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 14, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 39, 0);
    model.result("pg1").run();

    model.title("\u5fae\u6cf5\u673a\u7406");

    model
         .description("\u5fae\u6cf5\u662f\u5fae\u6d41\u4f53\u7cfb\u7edf\u7684\u5173\u952e\u7ec4\u4ef6\uff0c\u4ece\u751f\u7269\u6d41\u4f53\u5904\u7406\u5230\u5fae\u7535\u5b50\u51b7\u5374\uff0c\u5176\u5e94\u7528\u8303\u56f4\u5341\u5206\u5e7f\u6cdb\u3002\u672c\u6a21\u578b\u6a21\u62df\u4e00\u79cd\u65e0\u9600\u5fae\u6cf5\u7684\u5de5\u4f5c\u673a\u7406\uff0c\u8fd9\u79cd\u65e0\u9600\u5fae\u6cf5\u65e8\u5728\u6709\u6548\u5730\u7528\u4e8e\u4f4e\u96f7\u8bfa\u6570\u6d41\u4f53\uff0c\u5e76\u514b\u670d\u6d41\u4f53\u52a8\u529b\u5b66\u53ef\u9006\u6027\uff0c\u53ef\u4ee5\u6700\u5927\u7a0b\u5ea6\u5730\u51cf\u5c11\u5835\u585e\u98ce\u9669\u5e76\u5bf9\u751f\u7269\u6750\u6599\u6ca1\u6709\u660e\u663e\u7684\u8d1f\u9762\u5f71\u54cd\uff0c\u56e0\u6b64\u901a\u5e38\u662f\u5fae\u6d41\u4f53\u7cfb\u7edf\u4e2d\u7684\u9996\u9009\u90e8\u4ef6\u3002\u6a21\u578b\u4e2d\u4f7f\u7528\u201c\u6d41-\u56fa\u8026\u5408\u201d\u63a5\u53e3\u6c42\u89e3\u6d41\u4f53\u7684\u6d41\u52a8\u548c\u76f8\u5173\u7684\u7ed3\u6784\u53d8\u5f62\u3002\u6b64\u5916\uff0c\u8fd8\u4f7f\u7528\u201c\u5168\u5c40\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b\u201d\u63a5\u53e3\u6765\u6f14\u793a\u5982\u4f55\u5bf9\u6574\u4e2a\u6cf5\u5faa\u73af\u4e2d\u7684\u603b\u6d41\u91cf\u8fdb\u884c\u65f6\u95f4\u89e3\u6790\u79ef\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("micropump_mechanism.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
