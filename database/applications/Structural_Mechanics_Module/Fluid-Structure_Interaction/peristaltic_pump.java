/*
 * peristaltic_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:09 by COMSOL 6.3.0.290. */
public class peristaltic_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

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

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.015, 0.1});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.01, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("t_on", "0.3[s]");
    model.param().descr("t_on", "\u6eda\u8f74\u6324\u538b\u65f6\u95f4");
    model.param().set("t_off", "1.2[s]");
    model.param().descr("t_off", "\u6eda\u8f74\u8131\u79bb\u65f6\u95f4");
    model.param().set("dt", "0.2[s]");
    model.param().descr("dt", "\u529b\u8fbe\u5230\u5cf0\u503c\u7684\u65f6\u95f4");
    model.param().set("z0", "0.03[m]");
    model.param().descr("z0", "\u6eda\u8f74\u542f\u52a8\u5904\u7684 z \u5750\u6807");
    model.param().set("v0", "0.03[m/s]");
    model.param().descr("v0", "\u6eda\u8f74\u7684\u5782\u76f4\u901f\u5ea6");
    model.param().set("width", "0.01[m]");
    model.param().descr("width", "\u9ad8\u65af\u529b\u5206\u5e03\u7684\u5bbd\u5ea6");
    model.param().set("Ttot", "1.5[s]");
    model.param().descr("Ttot", "\u4e00\u6b21\u6cf5\u5faa\u73af\u7684\u603b\u65f6\u95f4");
    model.param().set("Lmax", "1.5e8[N/m^2]");
    model.param().descr("Lmax", "\u6700\u5927\u8f7d\u8377");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "t_on/dt");
    model.component("comp1").func("step1").set("smooth", 2);
    model.component("comp1").func().create("step2", "Step");
    model.component("comp1").func("step2").set("location", "t_off/dt");
    model.component("comp1").func("step2").set("from", 1);
    model.component("comp1").func("step2").set("to", 0);
    model.component("comp1").func("step2").set("smooth", 2);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "step1(ts)*step2(ts)*exp(-(zs-(z0+v0*ts*dt)/width)^2/2)");
    model.component("comp1").func("an1").set("args", "zs,ts");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(3);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("inflow", "intop1(2*pi*r*w_fluid)");
    model.component("comp1").variable("var1").descr("inflow", "\u6d41\u5165");
    model.component("comp1").variable("var1").set("outflow", "intop2(2*pi*r*w_fluid)");
    model.component("comp1").variable("var1").descr("outflow", "\u6d41\u51fa");

    model.component("comp1").common("free1").selection().set(1);

    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(2, 3);
    model.component("comp1").physics("spf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "netflow", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "netflowt-(outflow+inflow)/2", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("description", "\u7d2f\u79ef\u6d41\u91cf", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").set("DependentVariableQuantity", "volume");
    model.component("comp1").physics("spf").feature("ge1").set("SourceTermQuantity", "volumepertime");
    model.component("comp1").physics("spf").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge2").setIndex("name", "Vpump", 0, 0);
    model.component("comp1").physics("spf").feature("ge2").setIndex("equation", "Vpumpt-outflow", 0, 0);
    model.component("comp1").physics("spf").feature("ge2").setIndex("description", "\u8f93\u9001\u4f53\u79ef", 0, 0);
    model.component("comp1").physics("spf").feature("ge2").set("DependentVariableQuantity", "volume");
    model.component("comp1").physics("spf").feature("ge2").set("SourceTermQuantity", "volumepertime");
    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("alpha_dM", "1e-2");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("beta_dK", "1e-3");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5, 6);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(7);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"-Lmax*load(z/width,t/dt)", "0", "0"});

    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(2, 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Nylon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("lighting", "phong");
    model.component("comp1").material("mat1").set("shininess", 500);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1e3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"5e-3"});

    model.study("std1").feature("time").set("tlist", "range(0,0.02,1.5)");
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
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
    model.result().dataset("rev1").set("data", "dset1");
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
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1solidrev");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u5e94\u529b, 3D (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").label("\u52a8\u7f51\u683c");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection().set(1, 2);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 76, 0);
    model.result("pg7").label("\u4f4d\u79fb (solid)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("resolution", "normal");
    model.result("pg7").label("\u4f4d\u79fb (solid)");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 36, 0);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("unit", "mm");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().numerical().create("meas1", "MeasureSurface");
    model.result().numerical("meas1").set("intvolume", true);
    model.result().numerical("meas1").selection().set(1);
    model.result().numerical("meas1").set("descractive", true);
    model.result().numerical("meas1").set("descr", "\u5185\u90e8\u4f53\u79ef");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u6d4b\u91cf 1");
    model.result().numerical("meas1").set("table", "tbl1");
    model.result().numerical("meas1").setResult();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("source", "table");
    model.result("pg8").feature("tblp1").set("table", "tbl1");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("\u5185\u90e8\u4f53\u79ef");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6d41\u5165\u548c\u6d41\u51fa");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6d41\u91cf (m^3/s)");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "inflow", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "outflow", 1);
    model.result("pg9").feature("glob1").set("titletype", "none");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u7d2f\u79ef\u548c\u8f93\u9001\u6d41\u91cf");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u4f53\u79ef (m^3)");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "netflow", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "Vpump", 1);
    model.result("pg10").feature("glob1").set("titletype", "none");
    model.result("pg10").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6d41\u52a8\u548c\u5e94\u529b\uff0c\u4e09\u7ef4");
    model.result("pg3").setIndex("looplevel", 36, 0);
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "solid.mises");
    model.result("pg3").feature("surf2").set("unit", "MPa");
    model.result("pg3").feature("surf2").set("colortable", "Prism");
    model.result("pg3").run();

    model.title("\u8815\u52a8\u6cf5");

    model
         .description("\u5728\u8fd9\u4e2a\u8815\u52a8\u6cf5\u793a\u4f8b\u4e2d\uff0c\u8f8a\u7b52\u6324\u538b\u67d4\u6027\u7ba1\uff0c\u4ece\u800c\u901a\u8fc7\u538b\u7f29\u9a71\u52a8\u6d41\u4f53\u901a\u8fc7\u8be5\u7ba1\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u6d41-\u56fa\u8026\u5408\u201d\u63a5\u53e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("peristaltic_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
