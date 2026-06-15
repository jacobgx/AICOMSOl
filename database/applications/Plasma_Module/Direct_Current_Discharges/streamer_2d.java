/*
 * streamer_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:03 by COMSOL 6.3.0.290. */
public class streamer_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Direct_Current_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.05, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("P", "760", "\u538b\u529b (Torr)");
    model.component("comp1").variable("var1").set("mue", "2.9e5[cm^2/s/V]/P", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1").set("mui", "2.6e3[cm^2/s/V]/P", "\u79bb\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1")
         .set("Ri", "alpha(plas.normE)*mue*plas.normE/plas.c_wM", "\u901f\u7387\u5e38\u6570");
    model.component("comp1").variable("var1")
         .set("DeL", "1800[cm^2/s]", "\u7eb5\u5411\u7535\u5b50\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("DeT", "2190[cm^2/s]", "\u6a2a\u5411\u7535\u5b50\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("ne0", "ne0_min+ne0_max*exp(- ((z-z0)/sz)^2-((r)/sr)^2 )", "\u521d\u59cb\u7535\u5b50\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("ne0_min", "1e8[cm^-3]", "\u6700\u5c0f\u521d\u59cb\u7535\u5b50\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("ne0_max", "1e14[cm^-3]", "\u6700\u5927\u521d\u59cb\u7535\u5b50\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("z0", "0.5[cm]", "\u8f74\u5411\u4f4d\u7f6e");
    model.component("comp1").variable("var1").set("sz", "0.027[cm]", "z \u7684\u8272\u6563");
    model.component("comp1").variable("var1").set("sr", "0.021[cm]", "r \u7684\u8272\u6563");
    model.component("comp1").variable("var1").set("V0", "52[kV]", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u6c64\u68ee\u7cfb\u6570");
    model.component("comp1").func("an1").set("funcname", "alpha");
    model.component("comp1").func("an1").set("expr", "5.7*760*exp(-260*760/x)");
    model.component("comp1").func("an1").setIndex("argunit", "V/cm", 0);
    model.component("comp1").func("an1").set("fununit", "cm^-1");
    model.component("comp1").func("an1").setIndex("plotargs", "1[kV/cm]", 0, 1);
    model.component("comp1").func("an1").setIndex("plotargs", "150e3[kV/cm]", 0, 2);

    model.component("comp1").physics("plas").prop("ElectronProperties")
         .set("MeanElectronEnergyModel", "LocalFieldApproximationE");
    model.component("comp1").physics("plas").prop("ShapeProperty").set("Formulation", "FEMQuadratic");
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir1").set("formula", "e+M=>e+e+M+");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir1").set("de", 10);
    model.component("comp1").physics("plas").feature("eir1").set("kf", "Ri");
    model.component("comp1").physics("plas").feature("M").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("M").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("M_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("M_1p").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("M_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("plas").feature("M_1p").set("um", "mui");
    model.component("comp1").physics("plas").feature("pes1")
         .set("mue", new String[]{"mue", "0", "0", "0", "mue", "0", "0", "0", "mue"});
    model.component("comp1").physics("plas").feature("pes1").set("SpecifyElectronDensityAndEnergy", "SpecifyAll");
    model.component("comp1").physics("plas").feature("pes1")
         .set("De", new String[]{"DeL", "0", "0", "0", "DeL", "0", "0", "0", "DeT"});
    model.component("comp1").physics("plas").feature("init1").set("neinit", "ne0");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(2);
    model.component("comp1").physics("plas").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("plas").feature("mct1").set("V0", "V0");
    model.component("comp1").physics("plas").feature("mct1").selection().set(3);
    model.component("comp1").physics("plas").create("ede1", "ElectronDensityAndEnergy", 1);
    model.component("comp1").physics("plas").feature("ede1").selection().set(2, 3);
    model.component("comp1").physics("plas").feature("ede1").set("fixne", true);
    model.component("comp1").physics("plas").feature("ede1").set("newall", "ne0_min");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "default");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 5);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 600);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view2").axis().set("xmin", -0.06);
    model.component("comp1").view("view2").axis().set("xmax", 0.06);
    model.component("comp1").view("view2").axis().set("ymin", 0);
    model.component("comp1").view("view2").axis().set("ymax", 1);
    model.component("comp1").view("view2").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view2").axis().set("yscale", 0.17);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("view", "view1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();

    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,2.5)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u52bf\u548c\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6");
    model.result("pg2").set("data", "mir1");
    model.result("pg2").set("view", "view1");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "plas.scharge");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "V");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u5b50\u5bc6\u5ea6\u7b49\u503c\u7ebf");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("levelmethod", "levels");
    model.result("pg3").feature("con1").set("levels", "range(1.0e19,1.0e19,1.3e20)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("view", "view2");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e26\u7535\u7269\u8d28\u6570\u5bc6\u5ea6");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "z");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("legend", false);
    model.result("pg4").feature("lngr2").set("expr", "plas.n_wM_1p");
    model.result("pg4").feature("lngr2").set("linestyle", "dotted");
    model.result("pg4").feature("lngr2").set("linecolor", "black");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u573a");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("ymin", "-2e7");
    model.result("pg5").set("ymax", 0);
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", "plas.Ez");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "z");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();

    model.title("\u6c2e\u6c14\u4e2d\u7684\u53cc\u5934\u6d41\u6ce8");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u7684\u7814\u7a76\u63cf\u8ff0\u4e86\u5927\u6c14\u538b\u4e0b\u6c2e\u6c14\u4e2d\u7684\u53cc\u5934\u6d41\u6ce8\u3002\u5176\u4e2d\u5728\u4e24\u4e2a\u7535\u6781\u4e4b\u95f4\u653e\u7f6e\u521d\u59cb\u7535\u5b50\u79cd\u5b50\uff0c\u5e76\u4e14\u7535\u6781\u4f1a\u5bf9\u6c14\u4f53\u65bd\u52a0 52\u00a0kV/cm \u7684\u521d\u59cb\u7535\u573a\u3002\u6b63\u3001\u8d1f\u6d41\u6ce8\u5411\u7535\u6781\u65b9\u5411\u4f20\u64ad\u3002\u6d41\u6ce8\u7684\u7535\u5b50\u5bc6\u5ea6\u3001\u7535\u573a\u548c\u4f20\u64ad\u901f\u5ea6\u4e0e Bessi\u00e8res \u7b49\u4eba\u53d1\u8868\u7684\u4eff\u771f\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("streamer_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
