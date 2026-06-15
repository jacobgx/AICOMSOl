/*
 * rowland_circle_spectrometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:25 by COMSOL 6.3.0.290. */
public class rowland_circle_spectrometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("R", "1[m]");
    model.param().descr("R", "\u7f57\u5170\u5706\u534a\u5f84");
    model.param().set("alpha", "5[deg]");
    model.param().descr("alpha", "\u5149\u6805\u6247\u5f62\u89d2");
    model.param().set("phi0", "30[deg]");
    model.param().descr("phi0", "\u91ca\u653e\u89d2\u5ea6 (+CCW)");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("angle", "360-2*alpha");
    model.component("comp1").geom("geom1").feature("c1").set("rot", "-90+alpha");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c2").set("r", "2*R");
    model.component("comp1").geom("geom1").feature("c2").set("angle", "alpha");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"0", "R"});
    model.component("comp1").geom("geom1").feature("c2").set("rot", "270-alpha/2");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 5, 6);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 2, 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").create("aux1", "AuxiliaryField", -1);
    model.component("comp1").physics("gop").feature("aux1").set("fieldVariableName", "m");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-R*sin(phi0)", 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "R*cos(phi0)", 1);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nw", 10, 0);
    model.component("comp1").physics("gop").feature("relg1").set("cax", new String[]{"-x", "-(y+R)", "0"});
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "2[deg]");
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "Uniform");
    model.component("comp1").physics("gop").feature("relg1").setIndex("lambda0Nval", 10, 0);
    model.component("comp1").physics("gop").create("grat1", "Grating", 1);
    model.component("comp1").physics("gop").feature("grat1").selection().set(3);
    model.component("comp1").physics("gop").feature("grat1").set("RaysToRelease", "Reflected");
    model.component("comp1").physics("gop").feature("grat1").set("dg", "1.5[um]");
    model.component("comp1").physics("gop").feature("grat1")
         .set("InterpretationOfGratingConstant", "ProjectedUnitCellWidth");
    model.component("comp1").physics("gop").feature("grat1").feature().remove("dfo1");
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo1", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo1").set("mg", -3);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo2", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo2").set("mg", -2);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo3", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo3").set("mg", -1);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo4", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo4").set("mg", 0);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo5", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo5").set("mg", 1);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo6", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo6").set("mg", 2);
    model.component("comp1").physics("gop").feature("grat1").feature().create("dfo7", "DiffractionOrder", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo7").set("mg", 3);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo1").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo1").set("aux0_aux1", -3);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo2").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo2").set("aux0_aux1", -2);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo3").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo3").set("aux0_aux1", -1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo4").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo5").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo5").set("aux0_aux1", 1);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo6").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo6").set("aux0_aux1", 2);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo7").set("cauxrefl_aux1", true);
    model.component("comp1").physics("gop").feature("grat1").feature("dfo7").set("aux0_aux1", 3);
    model.component("comp1").physics("gop").create("wall1", "Wall", 1);
    model.component("comp1").physics("gop").feature("wall1").selection().set(1, 2, 4, 5);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 4*R");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9\uff0c\u884d\u5c04\u7ea7");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "m");
    model.result("pg1").feature("rtrj1").feature("col1").set("descractive", true);
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u884d\u5c04\u7ea7\u6570");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Traffic");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5c04\u7ebf\u8f68\u8ff9\uff0c\u771f\u7a7a\u6ce2\u957f (nm)");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg2").feature("rtrj1").feature("col1").set("unit", "nm");
    model.result("pg2").feature("rtrj1").feature("col1").set("colortable", "Spectrum");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg2").feature("rtrj1").feature("filt1").set("logicalexpr", "m!=0");
    model.result("pg2").run();
    model.result("pg2").create("rtrj2", "RayTrajectories");
    model.result("pg2").feature("rtrj2").set("linetype", "tube");
    model.result("pg2").feature("rtrj2").create("filt1", "RayTrajectoriesFilter");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj2").feature("filt1").set("type", "logical");
    model.result("pg2").feature("rtrj2").feature("filt1").set("logicalexpr", "m==0");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u4e8c\u7ef4\u76f4\u65b9\u56fe");
    model.result("pg3").set("data", "ray1");
    model.result("pg3").create("hist1", "Histogram");
    model.result("pg3").feature("hist1").set("xexpr", "atan2(-x,y)");
    model.result("pg3").feature("hist1").set("xunit", "\u00b0");
    model.result("pg3").feature("hist1").set("yexpr", "m");
    model.result("pg3").feature("hist1").set("ydescractive", true);
    model.result("pg3").feature("hist1").set("ydescr", "\u884d\u5c04\u7ea7");
    model.result("pg3").feature("hist1").set("ymethod", "limits");
    model.result("pg3").feature("hist1").set("ylimits", "range(-3.5,1,3.5)");
    model.result("pg3").feature("hist1").set("function", "discrete");
    model.result("pg3").feature("hist1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").run();

    model.title("\u7f57\u5170\u5706\u5149\u8c31\u4eea");

    model
         .description("\u7f57\u5170\u5706\u662f\u4e00\u4e2a\u534a\u5f84\u4e3a R \u7684\u5706\uff0c\u4e0e\u534a\u5f84\u4e3a 2R \u7684\u51f9\u9762\u5f2f\u66f2\u884d\u5c04\u5149\u6805\u76f8\u5207\u3002\u5982\u679c\u5165\u5c04\u5149\u7684\u5165\u5c04\u72ed\u7f1d\u4f4d\u4e8e\u8fd9\u4e2a\u5706\u4e0a\uff0c\u5219\u5149\u6805\u53cd\u5c04\u7684\u5149\u7ebf\u5c06\u6839\u636e\u6ce2\u957f\u548c\u884d\u5c04\u7ea7\u5728\u540c\u4e00\u4e2a\u5706\u7684\u4e0d\u540c\u70b9\u4e0a\u805a\u7126\u3002\u8fd9\u662f\u4e00\u4e2a\u57fa\u672c\u7684\u4e8c\u7ef4\u7f57\u5170\u5706\u5149\u8c31\u4eea\u6559\u5b66\u6848\u4f8b\uff0c\u6f14\u793a\u5982\u4f55\u5b9a\u4e49\u51f9\u9762\u5f2f\u66f2\u884d\u5c04\u5149\u6805\uff0c\u6307\u5b9a\u8981\u91ca\u653e\u7684\u884d\u5c04\u7ea7\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u8f85\u52a9\u56e0\u53d8\u91cf\u6765\u8ddf\u8e2a\u5df2\u91ca\u653e\u5149\u7ebf\u7684\u884d\u5c04\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("rowland_circle_spectrometer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
