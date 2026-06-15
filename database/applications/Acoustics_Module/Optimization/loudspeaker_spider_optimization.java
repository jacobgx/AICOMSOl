/*
 * loudspeaker_spider_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_spider_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("loudspeaker_spider_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel8");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("F0", "1[N]", "\u626b\u63cf\u7684\u529b\u53c2\u6570");
    model.param().set("Fsign", "-1", "\u529b\u7b26\u53f7");
    model.param().set("susp_stiff0", "1/susp_comp0", "\u76ee\u6807\u521a\u5ea6");
    model.param().set("susp_comp0", "0.3432[mm/N]", "\u76ee\u6807\u67d4\u5ea6");
    model.param()
         .set("Fmax", "susp_stiff0*z_length", "\u8fbe\u5230\u4f4d\u79fb\u6240\u9700\u7684\u6700\u5927\u529b");
    model.param().set("z_length", "7[mm]", "\u97f3\u5708\u7684\u4f4d\u79fb");
    model.param().set("max_disp", "3[mm]", "\u81ea\u7531\u5f62\u72b6\u8fb9\u754c\u7684\u6700\u5927\u4f4d\u79fb");
    model.param().set("mesh_size", "1.5[mm]", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u4f18\u5316\u76ee\u6807");
    model.component("comp1").variable("var1").set("obj_1", "(susp_stiff-susp_stiff0)^2*(1[m/N])^2");
    model.component("comp1").variable("var1").descr("obj_1", "\u4f18\u5316\u76ee\u6807");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u89d2\u53d8\u91cf");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(32);
    model.component("comp1").variable("var2").set("angle", "atan2(nZ,nR)");
    model.component("comp1").variable("var2").descr("angle", "\u6cd5\u5411\u89d2");
    model.component("comp1").variable("var2").set("step", "if(s<0.1,s/0.1,if(0.9<s,(1-s)/0.1,1))");
    model.component("comp1").variable("var2").descr("step", "\u89d2\u5e73\u6ed1\u6b65\u8fdb");
    model.component("comp1").variable("var2").set("nRApprox", "cos(-pi/2*(1-step)+angle*step)");
    model.component("comp1").variable("var2").descr("nRApprox", "\u6cd5\u7ebf\u7684 R \u5206\u91cf");
    model.component("comp1").variable("var2").set("nZApprox", "sin(-pi/2*(1-step)+angle*step)");
    model.component("comp1").variable("var2").descr("nZApprox", "\u6cd5\u7ebf\u7684 Z \u5206\u91cf");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("probename", "susp_disp");
    model.component("comp1").probe("dom1").selection().named("geom1_sel1");
    model.component("comp1").probe("dom1").set("expr", "w");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe("dom1").set("descr", "\u4f4d\u79fb");
    model.component("comp1").probe().create("dom2", "Domain");
    model.component("comp1").probe("dom2").set("intsurface", true);
    model.component("comp1").probe("dom2").set("intvolume", true);
    model.component("comp1").probe("dom2").set("probename", "susp_stiff");
    model.component("comp1").probe("dom2").selection().named("geom1_sel1");
    model.component("comp1").probe("dom2").set("expr", "F0*Fsign/w");
    model.component("comp1").probe("dom2").set("unit", "N/mm");
    model.component("comp1").probe("dom2").set("descractive", true);
    model.component("comp1").probe("dom2").set("descr", "\u60ac\u6302\u521a\u5ea6");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().set(32);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"Rg", "-49.7[mm]"});
    model.component("comp1").cpl("genext1").set("srcframe", "geometry");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common("fsd1").selection().named("geom1_sel6");
    model.component("comp1").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp1").common("fsb1").selection().set(32);
    model.component("comp1").common("fsb1").set("maximumDisplacementType", "Box");
    model.component("comp1").common("fsb1").set("maximumDisplacement", "max_disp");
    model.component("comp1").common("fsb1").set("filterRadiusType", "Small");
    model.component("comp1").common().create("pres1", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().geom("geom1", 1);
    model.component("comp1").common("pres1").selection().set(34);
    model.component("comp1").common("pres1")
         .set("prescribedDeformation", new String[]{"genext1(fsd1.dRg-nRApprox*0.4[mm])", "0", "genext1(fsd1.dZg-nZApprox*0.4[mm])-0.4[mm]"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Composite");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material("mat1").selection().named("geom1_sel2");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Cloth");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material("mat2").selection().named("geom1_sel3");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Foam");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.4");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "67[kg/m^3]");
    model.component("comp1").material("mat3").selection().named("geom1_sel5");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Coil");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material("mat4").selection().named("geom1_sel1");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Glass Fiber");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material("mat5").selection().named("geom1_sel4");

    model.component("comp1").physics("solid").selection().named("geom1_unisel1");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("solid").feature("bl1").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bl1").set("force", new String[]{"0", "0", "F0*Fsign"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_sel7");
    model.component("comp1").physics("solid").create("fix2", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix2").selection().named("geom1_sel8");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "mesh_size");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("geom1_sel7");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("geom1_sel8");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u4f20\u7edf\u8bbe\u8ba1");
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("probesel", "none");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/fix2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "F0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N", 0);
    model.study("std1").feature("stat").setIndex("pname", "F0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N", 0);
    model.study("std1").feature("stat").setIndex("pname", "Fsign", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "-1 1", 0);
    model.study("std1").feature("stat").setIndex("pname", "F0", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "N", 1);
    model.study("std1").feature("stat").setIndex("pname", "F0", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "N", 1);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "Fmax/1000 Fmax/20 range(Fmax/10,Fmax/10,Fmax*1.15)", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
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
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
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
    model.result().create("pg3", "PlotGroup2D");
    model.result().dataset().duplicate("dset1g1", "dset1");
    model.result().dataset("dset1g1").label("\u7814\u7a76 1 - \u4f20\u7edf\u8bbe\u8ba1/\u89e3 1 (1) - \u51e0\u4f55");
    model.result().dataset("dset1g1").set("frametype", "geometry");
    model.result("pg3").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("frametype", "geometry");
    model.result("pg3").set("edgecolor", "gray");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("data", "dset1g1");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg3").feature("arwl1").set("scaleactive", true);
    model.result("pg3").feature("arwl1").set("scale", "1");
    model.result("pg3").feature("arwl1").set("placement", "uniform");
    model.result("pg3").feature("arwl1").set("arrowcount", 200);
    model.result("pg3").feature("arwl1").create("col1", "Color");
    model.result("pg3").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg3").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").feature("arwl1").create("sel1", "Selection");
    model.result("pg3").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(12);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18);
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u4f20\u7edf\u8bbe\u8ba1\u7ed3\u679c");

    model.study().create("std2");
    model.study("std2").feature().copy("stat", "std1/stat");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("objectivesolution", "max");
    model.study("std2").feature("sho").set("movelimitactive", false);
    model.study("std2").feature("sho").setIndex("optobj", "log10(comp1.obj_1)", 0);
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/fix1"});
    model.study("std2").feature("stat").setIndex("plistarr", "Fmax/1000 Fmax/20 range(Fmax/10,Fmax/10,Fmax)", 1);
    model.study("std2").label("\u7814\u7a76 2 - \u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u5e94\u529b (solid) 1");
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
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2solidrev");
    model.result("pg4").label("\u5e94\u529b, 3D (solid) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result("pg4").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("descractive", true);
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", "1");
    model.result().create("pg5", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u7814\u7a76 2 - \u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg5").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "geometry");
    model.result("pg5").set("edgecolor", "gray");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "fromtheme");
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("data", "dset2g1");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg5").feature("arwl1").set("scaleactive", true);
    model.result("pg5").feature("arwl1").set("scale", "1");
    model.result("pg5").feature("arwl1").set("placement", "uniform");
    model.result("pg5").feature("arwl1").set("arrowcount", 200);
    model.result("pg5").feature("arwl1").create("col1", "Color");
    model.result("pg5").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg5").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg5").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg5").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg5").feature("arwl1").create("sel1", "Selection");
    model.result("pg5").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection().set(12);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg3").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18);
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").set("placement", "gausspoints");
    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u4f18\u5316\u7ed3\u679c");

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u529b vs. \u4f4d\u79fb");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u4f4d\u79fb (mm)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u4f5c\u7528\u529b (N)");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "F0*Fsign", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "", 0);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "susp_disp");
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "\u4f20\u7edf\u8bbe\u8ba1", 0);
    model.result("pg7").feature("glob1").set("linestyle", "none");
    model.result("pg7").feature("glob1").set("linemarker", "circle");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset2");
    model.result("pg7").feature("glob2").setIndex("expr", "susp_disp/susp_comp0", 0);
    model.result("pg7").feature("glob2").set("linestyle", "dotted");
    model.result("pg7").feature("glob2").set("linecolor", "gray");
    model.result("pg7").feature("glob2").set("linemarker", "none");
    model.result("pg7").feature("glob2").setIndex("legends", "\u7406\u60f3\u7684\u60ac\u6302\u7cfb\u7edf", 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("glob3", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob3").set("data", "dset2");
    model.result("pg7").feature("glob3").setIndex("legends", "\u4f18\u5316\u8bbe\u8ba1", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u67d4\u5ea6");
    model.result("pg8").set("ylabel", "\u67d4\u5ea6 C<sub>MS</sub>(x) (mm/N)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "1/susp_stiff", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "mm/N", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob2").setIndex("expr", "1/susp_stiff0", 0);
    model.result("pg8").feature("glob2").setIndex("unit", "mm/N", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob3").setIndex("expr", "1/susp_stiff", 0);
    model.result("pg8").feature("glob3").setIndex("unit", "mm/N", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").run();
    model.result("pg2").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("surf1").feature().remove("def");
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature().remove("def");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();

    model.title("\u626c\u58f0\u5668\u5b9a\u5fc3\u652f\u7247\u4f18\u5316");

    model
         .description("\u626c\u58f0\u5668\u60ac\u67b6\u7684\u4f5c\u7528\u662f\u5c06\u7eb8\u76c6\u548c\u9632\u5c18\u76d6\u4fdd\u6301\u5728\u9002\u5f53\u7684\u4f4d\u7f6e\uff0c\u4ece\u800c\u907f\u514d\u97f3\u5708\u4ea7\u751f\u4efb\u4f55\u6643\u52a8\u3002\u5728\u4f4e\u9891\u65f6\uff0c\u7eb8\u76c6\u548c\u9632\u5c18\u76d6\u7684\u4f4d\u79fb\u975e\u5e38\u660e\u663e\uff0c\u60ac\u67b6\u7684\u521a\u5ea6\u968f\u7740\u97f3\u5708\u7684\u51b2\u7a0b\u800c\u53d8\u5316\u3002\u8fd9\u79cd\u53d8\u5316\uff08\u6216\u975e\u7ebf\u6027\uff09\u5728\u626c\u58f0\u5668\u4ea7\u751f\u7684\u5931\u771f\u4e2d\u5177\u6709\u91cd\u8981\u7684\u4f5c\u7528\u3002\n\n\u672c\u6a21\u578b\u6f14\u793a\u5f62\u72b6\u4f18\u5316\u5728\u5b9a\u5fc3\u652f\u7247\uff08\u626c\u58f0\u5668\u60ac\u67b6\u7684\u6784\u6210\u90e8\u4ef6\uff09\u8bbe\u8ba1\u4e2d\u7684\u5e94\u7528\u3002\u901a\u8fc7\u6539\u53d8\u5b9a\u5fc3\u652f\u7247\u7684\u5f62\u72b6\uff0c\u53ef\u4ee5\u521b\u5efa\u4e00\u4e2a\u5728\u6574\u4e2a\u97f3\u5708\u8fd0\u52a8\u8303\u56f4\u5185\u5448\u7ebf\u6027\u8fd0\u52a8\uff08\u6052\u5b9a\u521a\u5ea6\uff09\u7684\u60ac\u6302\u7cfb\u7edf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("loudspeaker_spider_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
