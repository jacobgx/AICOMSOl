/*
 * pulmonary_artery_stenosis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:09 by COMSOL 6.3.0.290. */
public class pulmonary_artery_stenosis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Tutorials");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");
    model.mesh("mpart1").create("imp1", "Import");
    model.mesh("mpart1").feature("imp1").set("filename", "pulmonary_artery_stenosis_mesh.mphbin");
    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").create("edg1", "CreateEdges");
    model.mesh("mpart1").feature("edg1").set("edgespec", "meshedge");
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.025474513285015567, 0, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05300163474086225, 0, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.06285868311038151, 0, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02882189543018464, 1, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05376491659015595, 1, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.061381714006174454, 1, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.030975205657410854, 2, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05344556168574242, 2, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05802667913487032, 2, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.03127001906263595, 3, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.052175887615079714, 3, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05400271560175294, 3, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.029926804420967845, 4, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.049717995267243284, 4, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05149142052129319, 4, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.027385809620836102, 5, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04701073651666218, 5, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05047984850105791, 5, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.024222180439672798, 6, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04574020824221641, 6, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05017567231851772, 6, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02124249525487873, 7, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04549752625558551, 7, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05113297630835324, 7, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.018918073090635684, 8, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04543944519436713, 8, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05317819527787116, 8, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.017721555132013584, 9, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.046673939298892464, 9, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05574683049261853, 9, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.018078636650821357, 10, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.049179917482312804, 10, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.058379977361690705, 10, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.019600350971719256, 11, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05101821677718443, 11, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.06072565265250042, 11, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02212936293007646, 12, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.052024285955543134, 12, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.0624360061590074, 12, 2);
    model.mesh("mpart1").run("edg1");
    model.mesh("mpart1").create("fac1", "CreateFaces");
    model.mesh("mpart1").feature("fac1").set("createdom", true);
    model.mesh("mpart1").feature("fac1").selection().add(34, 35);
    model.mesh("mpart1").feature("fac1").set("groupadjedg", false);
    model.mesh("mpart1").run("fac1");
    model.mesh("mpart1").create("ada1", "Adapt");
    model.mesh("mpart1").feature("ada1").set("method", "modify");
    model.mesh("mpart1").feature("ada1").set("sizeexpr", "0.003");
    model.mesh("mpart1").run("ada1");
    model.mesh("mpart1").create("remf1", "RemeshFaces");
    model.mesh("mpart1").feature("remf1").selection().all();
    model.mesh("mpart1").feature("remf1").feature("size").set("table", "cfd");
    model.mesh("mpart1").feature("remf1").feature("size").set("hauto", 4);
    model.mesh("mpart1").run("remf1");
    model.mesh("mpart1").create("dom1", "CreateDomains");
    model.mesh("mpart1").run("dom1");
    model.mesh("mpart1").create("ftet1", "FreeTet");
    model.mesh("mpart1").feature("ftet1").feature("size").set("table", "cfd");
    model.mesh("mpart1").feature("ftet1").feature("size").set("hauto", 6);
    model.mesh("mpart1").run("ftet1");
    model.mesh("mpart1").create("bl1", "BndLayer");
    model.mesh("mpart1").feature("bl1").create("blp", "BndLayerProp");
    model.mesh("mpart1").feature("bl1").set("sharpcorners", "trim");
    model.mesh("mpart1").feature("bl1").feature("blp").selection().set(2, 4, 5, 6, 8, 9, 12, 13, 17, 18);
    model.mesh("mpart1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.mesh("mpart1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.mesh("mpart1").run("fin");

    model.title(null);

    model.description("");

    model.label("pulmonary_artery_stenosis_geom_sequence.mph");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("source", "sequence");
    model.component("comp1").mesh("mesh1").feature("imp1").set("buildsource", true);
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("fp").feature("fp1").set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp1").physics("fp").feature("fp1").set("nonNewtonianModels", "Carreau");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().set(5);
    model.component("comp1").physics("fp").feature("porous1").feature("fluid1")
         .set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp1").physics("fp").feature("porous1").feature("fluid1").set("nonNewtonianModels", "Carreau");
    model.component("comp1").physics("fp").feature("porous1").feature("fluid1").set("correctionCoefficient", "cap");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1120"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Carreau", "Carreau", "Non-Newtonian_Carreau_model");
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("mu0", new String[]{"0.0560"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("mu_inf", new String[]{"0.00345"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("lam_car", new String[]{"3.313"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("n_car", new String[]{"0.3568"});
    model.component("comp1").material("mat1").propertyGroup("def").set("porosity", new String[]{"0.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-7"});

    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").selection().set(1);

    model.func().create("int1", "Interpolation");
    model.func("int1")
         .set("table", new String[][]{{"0", "9.518e-02"}, 
         {"8.110e-02", "1.912e-01"}, 
         {"1.008e-01", "2.611e-01"}, 
         {"1.295e-01", "3.228e-01"}, 
         {"1.596e-01", "2.985e-01"}, 
         {"1.906e-01", "2.417e-01"}, 
         {"2.206e-01", "1.545e-01"}, 
         {"2.313e-01", "2.092e-01"}, 
         {"2.413e-01", "3.024e-01"}, 
         {"2.520e-01", "3.996e-01"}, 
         {"2.800e-01", "5.494e-01"}, 
         {"3.504e-01", "6.698e-01"}, 
         {"3.822e-01", "6.383e-01"}, 
         {"4.319e-01", "5.684e-01"}, 
         {"4.522e-01", "5.400e-01"}, 
         {"4.702e-01", "5.511e-01"}, 
         {"5.013e-01", "5.986e-01"}, 
         {"5.307e-01", "6.411e-01"}, 
         {"6.517e-01", "7.685e-01"}, 
         {"7.031e-01", "6.975e-01"}, 
         {"7.226e-01", "6.499e-01"}, 
         {"7.339e-01", "6.012e-01"}, 
         {"7.509e-01", "4.979e-01"}, 
         {"7.606e-01", "4.017e-01"}, 
         {"7.702e-01", "2.478e-01"}, 
         {"8e-01", "9.518e-02"}});
    model.func("int1").set("interp", "piecewisecubic");
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "v_in");
    model.func("an1").set("expr", "int1(t)");
    model.func("an1").set("args", "t");
    model.func("an1").set("periodic", true);
    model.func("an1").set("periodicupper", 0.8);
    model.func("an1").set("fununit", "m/s");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").setIndex("plotargs", 2.4, 0, 2);

    model.component("comp1").physics("fp").feature("inl1").set("U0in", "v_in(try_catch(t,0))");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().set(4, 7, 10, 19);

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.02,2.4)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (fp)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result("pg1").label("\u901f\u5ea6 (fp)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 8);
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set();
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.035);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str1").set("tuberadiusscale", "5e-4");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "p");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "Garnet");
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "reverse");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().set(3, 6, 9, 13, 17);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "surf1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().set(18);
    model.result().numerical("av1").setIndex("looplevelinput", "interp", 0);
    model.result().numerical("av1").setIndex("interp", "range(1.6,0.02,2.4)", 0);
    model.result().numerical("av1").setIndex("expr", "p", 0);
    model.result().numerical("av1").setIndex("unit", "mmHg", 0);
    model.result().numerical("av1").setIndex("descr", "Pressure, proximal", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical().duplicate("av2", "av1");
    model.result().numerical("av2").selection().set(15);
    model.result().numerical("av2").setIndex("descr", "Pressure, distal", 0);
    model.result().numerical("av2").set("table", "tbl1");
    model.result().numerical("av2").appendResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "table");
    model.result("pg2").feature("tblp1").set("table", "tbl1");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(18);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().set(15);

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "aveop2(p)/aveop1(p)", 0);
    model.result().numerical("gev1").setIndex("descr", "QPPR", 0);
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{94}, 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl2");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();

    model.title("\u72ed\u7a84\u80ba\u52a8\u8109\u4e2d\u7684\u8840\u6d41");

    model
         .description("\u672c\u4f8b\u91c7\u7528\u591a\u5b54\u4ecb\u8d28\u7406\u8bba\u5904\u7406\u80ba\u52a8\u8109\u72ed\u7a84\u95ee\u9898\uff0c\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u4e00\u4e2a\u7ed3\u5408\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\u7684\u975e\u725b\u987f\u6d41\u4f53\u6a21\u578b\u3002\u5176\u4e2d\u4f7f\u7528 Carreau \u6a21\u578b\u5bf9\u8840\u6db2\u7684\u975e\u725b\u987f\u7279\u6027\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh("mpart1").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pulmonary_artery_stenosis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
