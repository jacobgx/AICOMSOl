/*
 * drug_delivery_mm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:11 by COMSOL 6.3.0.290. */
public class drug_delivery_mm {

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

    model.component("comp1").geom("geom1").insertFile("drug_delivery_mm_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("u0", "0.001[m/s]");
    model.param().descr("u0", "\u6db2\u6ef4\u901f\u5ea6 (m/s)");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(4);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("n_abs", "intop1(2*pi*r*c)");
    model.component("comp1").variable("var1").descr("n_abs", "\u4f20\u9012\u7684\u6469\u5c14\u6570");
    model.component("comp1").variable("var1").set("z_pnt", "intop2(z)");
    model.component("comp1").variable("var1").descr("z_pnt", "\u6db2\u6ef4\u9876\u90e8\u4f4d\u7f6e");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "6e-4");
    model.component("comp1").func("rect1").set("upper", "8e-4");
    model.component("comp1").func("rect1").set("smooth", "5e-5");
    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "1e-4");
    model.component("comp1").func("step1").set("smooth", "1e-4");

    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(1, 3, 5, 6, 7, 10, 11, 12, 13, 14);

    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(10, 11, 12, 13, 14);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "NavierSlip");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(9);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u0*step1(t/1[s])");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(2);
    model.component("comp1").physics("spf").create("ffi1", "FluidFluidInterface", 1);
    model.component("comp1").physics("spf").feature("ffi1").selection().set(15, 16);
    model.component("comp1").physics("spf").feature("ffi1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("spf").feature("ffi1").set("ReverseNormal", true);
    model.component("comp1").physics("spf").feature("ffi1").feature("cnta1")
         .set("thetac", "3*pi*(1-rect1(z/1[m]))/4+7*pi*rect1(z/1[m])/8");
    model.component("comp1").physics("spf").feature("ffi1").feature("cnta1")
         .set("ConstrainNormalWallVelocity", true);
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").selection().set(3);
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"5E-9", "0", "0", "0", "5E-9", "0", "0", "0", "5E-9"});
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().set(12);
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("J0", "rect1(z/1[m])*0.001[mol/(m^2*s)]", 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1.25"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"2e-5"});
    model.component("comp1").material("mat1").selection().set(1, 2, 4, 5);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").mesh("mesh1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh1").feature("sca1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("sca1").selection().set(12, 15, 16);
    model.component("comp1").mesh("mesh1").feature("sca1").set("scale", 0.5);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "3E-5");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "u0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "u0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "0.0001 0.00015 0.0002 0.00025 0.0004 0.0006 0.0008 0.001", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.5,10)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.z_pnt<0.0004", 0);
    model.sol("sol1").feature("t1").set("atolglobalmethod", "unscaled");
    model.sol("sol1").feature("t1").set("storeudot", false);

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
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 8, 1);
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
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").setIndex("looplevel", 8, 1);
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
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").setIndex("looplevel", 8, 1);
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
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cr", "tds.tflux_cz"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(3);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "rev1");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").setIndex("looplevel", 8, 1);
    model.result("pg5").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").setIndex("looplevel", 8, 1);
    model.result("pg6").label("\u52a8\u7f51\u683c");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4, 4});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 10);
    model.result("pg1").feature("str1").selection().set(2, 9);
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").active(false);
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{4, 4});
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevelinput", "first", 1);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "n_abs", 0);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "n_abs", 0);
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "u0");
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").run();
    model.result("pg5").run();

    model.title("\u836f\u7269\u8f93\u9001\u7cfb\u7edf");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u63d0\u4f9b\u6d53\u5ea6\u53ef\u53d8\u6c34\u6eb6\u6027\u836f\u7269\u7684\u836f\u7269\u8f93\u9001\u7cfb\u7edf\u7684\u5de5\u4f5c\u539f\u7406\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("drug_delivery_mm.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
