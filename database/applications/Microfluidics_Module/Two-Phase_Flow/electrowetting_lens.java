/*
 * electrowetting_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:11 by COMSOL 6.3.0.290. */
public class electrowetting_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", "0");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1.5, 1});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.55, 0);

    model.param().set("theta0", "140[deg]");
    model.param().descr("theta0", "\u96f6\u7535\u538b\u63a5\u89e6\u89d2");
    model.param().set("gamma", "0.05[N/m]");
    model.param().descr("gamma", "\u8868\u9762\u5f20\u529b");
    model.param().set("muoil", "8e-3[Pa*s]");
    model.param().descr("muoil", "\u7edd\u7f18\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("epsr", "2.65");
    model.param().descr("epsr", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("d_f", "3[um]");
    model.param().descr("d_f", "\u7535\u4ecb\u8d28\u539a\u5ea6");
    model.param().set("Vapp", "120[V]");
    model.param().descr("Vapp", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("theta", "acos(cos(theta0)+Vapp^2*epsr*epsilon0_const/(2*gamma*d_f))");
    model.component("comp1").variable("var1").descr("theta", "\u63a5\u89e6\u89d2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7edd\u7f18\u6d41\u4f53");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"muoil"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6c2f\u5316\u9502\u6eb6\u6db2");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"1.5e-3"});

    model.component("comp1").physics("spf").create("ffi1", "FluidFluidInterface", 1);
    model.component("comp1").physics("spf").feature("ffi1").selection().set(4);
    model.component("comp1").physics("spf").feature("ffi1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("spf").feature("ffi1").set("sigma", "gamma");
    model.component("comp1").physics("spf").feature("ffi1").feature("cnta1").set("thetac", "theta");
    model.component("comp1").physics("spf").feature("ffi1").feature("cnta1")
         .set("ConstrainNormalWallVelocity", true);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(6, 7);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "NavierSlip");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(6);

    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(1, 3, 6, 7);

    model.component("comp1").mesh("mesh1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh1").feature("sca1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("sca1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("sca1").set("scale", 0.2);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "theta0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "theta0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "muoil", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10e-3 30e-3 50e-3", 0);
    model.study("std1").feature("time").set("tlist", "range(0,1e-3,5e-2)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").set("dataisaxisym", "off");
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
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1, 2);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "r (mm)");
    model.result("pg1").set("ylabel", "z (mm)");
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("xlabel", "r (mm)");
    model.result("pg2").set("ylabel", "z (mm)");
    model.result("pg2").run();
    model.result("pg2").feature().remove("con1");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").run();
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").set("color", "white");
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "spf.rho");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u5f2f\u6708\u9762\u9ad8\u5ea6 (mm)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "intop1(1)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5f2f\u6708\u9762\u9ad8\u5ea6", 0);
    model.result("pg6").feature("glob1").set("xdataparamunit", "ms");
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();

    model.title("\u7535\u6da6\u6e7f\u900f\u955c");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u7531\u7535\u4ecb\u8d28\u4e0a\u7684\u7535\u6da6\u6e7f (EWOD) \u6548\u5e94\u9a71\u52a8\u7684\u53ef\u8c03\u900f\u955c\u3002\u4e24\u79cd\u4e0d\u6df7\u6eb6\u6d41\u4f53\uff08\u4e00\u79cd\u5bfc\u7535\uff0c\u53e6\u4e00\u79cd\u7edd\u7f18\uff09\u4e4b\u95f4\u4ea7\u751f\u7684\u5f2f\u6708\u9762\u7528\u6765\u5f62\u6210\u8be5\u900f\u955c\u3002\u5728\u5bfc\u7535\u6d41\u4f53\u4e0e\u900f\u955c\u8154\u58c1\u9762\u7684\u4ecb\u8d28\u6d82\u5c42\u7535\u6781\u4e4b\u95f4\u65bd\u52a0\u7535\u573a\u3002\u901a\u8fc7\u6539\u53d8\u7535\u573a\u53ef\u4ee5\u6539\u53d8\u4e24\u79cd\u6d41\u4f53\u4e4b\u95f4\u63a5\u89e6\u7ebf\u7684\u63a5\u89e6\u89d2\u5ea6\uff0c\u4ece\u800c\u6539\u53d8\u900f\u955c\u7684\u5f62\u72b6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("electrowetting_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
