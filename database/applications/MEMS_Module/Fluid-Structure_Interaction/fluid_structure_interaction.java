/*
 * fluid_structure_interaction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:57 by COMSOL 6.3.0.290. */
public class fluid_structure_interaction {

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

    model.param().set("U", "3.33[cm/s]");
    model.param().descr("U", "\u5165\u53e3\u7684\u7a33\u6001\u5e73\u5747\u901f\u5ea6");
    model.param().set("H", "100[um]");
    model.param().descr("H", "\u901a\u9053\u9ad8\u5ea6");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").set("expr", "t^2/sqrt((0.04[s^2]-t^2)^2+(0.1[s]*t)^2)");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"300", "H"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{5, 47.5});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{100, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 2.5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("solid").selection().set(2);

    model.component("comp1").common("free1").selection().set(1);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U*an1(t)");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(8);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"2e5"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7850"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.005,0.75) range(1,0.25,4)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0001");
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
    model.result("pg3").setIndex("looplevel", 164, 0);
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
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 164, 0);
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("startmethod", "coord");
    model.result("pg1").feature("str1").set("xcoord", "0^(range(1,15)) 125*1^(range(1,2))");
    model.result("pg1").feature("str1").set("ycoord", "range(0,100/14,100) 20 5");
    model.result("pg1").feature("str1").set("color", "red");
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
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0.025,0.025,0.5)");
    model.result().export("anim1").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u53d8\u5f62\u7f51\u683c\u4e0e\u51e0\u4f55");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "blue");
    model.result("pg5").feature("surf1").set("wireframe", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 156, 0);
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"xt", "yt"});
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").create("arws2", "ArrowSurface");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7f51\u683c\u901f\u5ea6");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "U*an1(t)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5165\u53e3\u5e73\u5747\u901f\u5ea6", 0);
    model.result("pg6").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 105);
    model.result().dataset("cpt1").set("pointy", 50);
    model.result("pg6").run();
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("data", "cpt1");
    model.result("pg6").feature("ptgr1").set("expr", "xt");
    model.result("pg6").feature("ptgr1").set("unit", "mm/s");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1")
         .setIndex("legends", "x \u65b9\u5411\u4e0a\u7684\u7f51\u683c\u901f\u5ea6 (mm/s)", 0);
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("expr", "x-X");
    model.result("pg6").feature("ptgr2").set("unit", "mm");
    model.result("pg6").feature("ptgr2")
         .setIndex("legends", "x \u65b9\u5411\u4e0a\u7684\u7f51\u683c\u4f4d\u79fb (mm)", 0);
    model.result("pg6").run();
    model.result("pg1").run();

    model.title("\u6d41-\u56fa\u8026\u5408");

    model
         .description("\u672c\u6a21\u578b\u4e3e\u4f8b\u8bf4\u660e\u4e86\u5982\u4f55\u4f7f\u7528\u201cMEMS \u6a21\u5757\u201d\u5bf9\u6d41-\u56fa\u8026\u5408 (FSI) \u8fdb\u884c\u5efa\u6a21\u3002\u5728\u6b64\u8fc7\u7a0b\u4e2d\uff0c\u9ecf\u6027\u529b\u548c\u7cfb\u7edf\u538b\u529b\u4f5c\u7528\u4e8e\u7ed3\u6784\u8868\u9762\uff0c\u5bf9\u5176\u65bd\u52a0\u529b\u3002\u7531\u4e8e\u8f6f\u7ed3\u6784\u7684\u53d8\u5f62\u8f83\u5927\uff0c\u56e0\u6b64\u4f1a\u6539\u53d8\u6d41\u4f53\u72b6\u6001\uff0c\u8fd9\u8868\u660e\u7ed3\u6784\u7684\u53d8\u5316\u4e0e\u6d41\u4f53\u52a8\u529b\u5b66\u76f8\u4e92\u5173\u8054\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("fluid_structure_interaction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
