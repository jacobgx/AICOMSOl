/*
 * gregory_maksutov_telescope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:16 by COMSOL 6.3.0.290. */
public class gregory_maksutov_telescope {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().label("\u53c2\u6570 1\uff1a\u900f\u955c\u89c4\u683c\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u901a\u7528");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lam1", "486.0[nm]", "\u6ce2\u957f 1");
    model.param("par2").set("lam2", "546.0[nm]", "\u6ce2\u957f 2");
    model.param("par2").set("lam3", "656.0[nm]", "\u6ce2\u957f 3");
    model.param("par2").set("lam_mean", "(lam1+lam2+lam3)/3", "\u5e73\u5747\u6ce2\u957f");
    model.param("par2").set("theta_x1", "0.000[deg]", "\u89c6\u573a\u89d2 1\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y1", "0.000[deg]", "\u89c6\u573a\u89d2 1\uff0cy \u5206\u91cf");
    model.param("par2").set("theta_x2", "0.000[deg]", "\u89c6\u573a\u89d2 2\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y2", "0.125[deg]", "\u89c6\u573a\u89d2 2\uff0cy \u5206\u91cf");
    model.param("par2").set("theta_x3", "0.000[deg]", "\u89c6\u573a\u89d2 3\uff0cx \u5206\u91cf");
    model.param("par2").set("theta_y3", "0.250[deg]", "\u89c6\u573a\u89d2 3\uff0cy \u5206\u91cf");
    model.param("par2").set("N_ring", "12", "\u516d\u8fb9\u73af\u6570");
    model.param("par2").set("P_nom", "200.0[mm]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param("par2").set("F_nom", "10.0", "\u6807\u79f0\u7126\u6bd4");
    model.param("par2").set("f_nom", "F_nom*P_nom", "\u6807\u79f0\u7126\u8ddd");
    model.param("par2").set("theta_Airy", "1.22*lam_mean/P_nom", "\u827e\u91cc\u6591\u89d2\u534a\u5f84");
    model.param("par2").set("r_Airy", "f_nom*theta_Airy", "\u827e\u91cc\u6591\u534a\u5f84");
    model.param("par2").set("vz", "-1", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("vx1", "tan(theta_x1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy1", "tan(theta_y1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2").set("vx2", "tan(theta_x2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy2", "tan(theta_y2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2").set("vx3", "tan(theta_x3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2").set("vy3", "tan(theta_y3)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2").set("dz", "50[mm]", "\u5149\u7ebf\u53d1\u5c04 z \u5750\u6807");
    model.param("par2")
         .set("dx1", "-dz*tan(theta_x1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy1", "-dz*tan(theta_y1)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 1\uff0cy \u5750\u6807");
    model.param("par2")
         .set("dx2", "-dz*tan(theta_x2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy2", "-dz*tan(theta_y2)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 2\uff0cy \u5750\u6807");
    model.param("par2")
         .set("dx3", "-dz*tan(theta_x3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cx \u5750\u6807");
    model.param("par2")
         .set("dy3", "-dz*tan(theta_y3)", "\u5149\u7ebf\u53d1\u5c04\uff0c\u89c6\u573a 3\uff0cy \u5750\u6807");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1")
         .label("\u683c\u91cc\u9ad8\u5229-\u9a6c\u514b\u82cf\u6258\u592b\u671b\u8fdc\u955c");
    model.component("comp1").geom("geom1").insertFile("gregory_maksutov_telescope_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Schott N-BK7 Glass");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2.51[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "0.858[J/(g*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.03961212E+00", "2.31792344E-01", "1.01046945E+00", "6.00069867E-03", "2.00179144E-02", "1.03560653E+02"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"1.86E-6", "1.31E-8", "-1.37E-11", "4.34E-7", "6.27E-10", "0.17"});
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "82.0[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.206");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.665"}, 
         {"2325", "0.793"}, 
         {"1970", "0.933"}, 
         {"1530", "0.992"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.998"}, 
         {"460", "0.997"}, 
         {"436", "0.997"}, 
         {"420", "0.997"}, 
         {"405", "0.997"}, 
         {"400", "0.997"}, 
         {"390", "0.996"}, 
         {"380", "0.993"}, 
         {"370", "0.991"}, 
         {"365", "0.988"}, 
         {"350", "0.967"}, 
         {"334", "0.905"}, 
         {"320", "0.77"}, 
         {"310", "0.574"}, 
         {"300", "0.292"}, 
         {"290", "0.063"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.36"}, 
         {"2325", "0.56"}, 
         {"1970", "0.84"}, 
         {"1530", "0.98"}, 
         {"1060", "0.997"}, 
         {"700", "0.996"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.996"}, 
         {"500", "0.994"}, 
         {"460", "0.993"}, 
         {"436", "0.992"}, 
         {"420", "0.993"}, 
         {"405", "0.993"}, 
         {"400", "0.992"}, 
         {"390", "0.989"}, 
         {"380", "0.983"}, 
         {"370", "0.977"}, 
         {"365", "0.971"}, 
         {"350", "0.92"}, 
         {"334", "0.78"}, 
         {"320", "0.52"}, 
         {"310", "0.25"}, 
         {"300", "0.05"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_sel1");

    model.component("comp1").physics("gop").selection().set(2);
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u53cd\u5c04\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_pi3_sel1");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx1", "dy1", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "P_nom/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_ring", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "vz"});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1").setIndex("lambda0vals", "lam1 lam2 lam3", 0);
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"dx2", "dy2", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"dx3", "dy3", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "vz"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 1750");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,atan2(qy,qx)>-pi/2)");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "custom");
    model.result("pg1").feature("surf1")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_pi1_sel2");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(5, 6, 7, 8, 9, 11, 18, 19, 22, 24);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().named("geom1_csel2_bnd");
    model.result("pg1").run();
    model.result("pg1").create("surf3", "Surface");
    model.result("pg1").feature("surf3").set("coloring", "uniform");
    model.result("pg1").feature("surf3").set("color", "custom");
    model.result("pg1").feature("surf3")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg1").feature("surf3").create("sel1", "Selection");
    model.result("pg1").feature("surf3").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("origin", "area");
    model.result("pg2").feature("spot1").set("circleactive", true);
    model.result("pg2").feature("spot1").set("circleradiusexpr", "r_Airy");
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg2").feature("spot1").feature("col1").set("unit", "nm");
    model.result("pg2").feature("spot1").feature("col1").set("rangecoloractive", true);
    model.result("pg2").feature("spot1").feature("col1").set("rangecolormin", 425);
    model.result("pg2").feature("spot1").feature("col1").set("rangecolormax", 675);
    model.result("pg2").feature("spot1").feature("col1").set("colortable", "Spectrum");
    model.result("pg2").run();

    model.title("\u683c\u91cc\u9ad8\u5229-\u9a6c\u514b\u82cf\u6258\u592b\u671b\u8fdc\u955c");

    model
         .description("\u683c\u91cc\u9ad8\u5229-\u9a6c\u514b\u82cf\u6258\u592b\u671b\u8fdc\u955c\u662f\u4e00\u79cd\u7b80\u5355\u7684\u6298\u53cd\u5c04\u671b\u8fdc\u955c\uff0c\u7531\u4e00\u4e2a\u7403\u9762\u6821\u6b63\u900f\u955c\u548c\u4e00\u4e2a\u7403\u9762\u4e3b\u955c\u7ec4\u6210\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u6821\u6b63\u900f\u955c\u548c\u4e3b\u955c\u5206\u522b\u7531\u201c\u5c04\u7ebf\u5149\u5b66\u201d\u96f6\u4ef6\u5e93\u4e2d\u7684\u201c\u7403\u9762\u900f\u955c\uff08\u4e09\u7ef4\uff09\u201d\u548c\u201c\u7403\u9762\u955c\uff08\u4e09\u7ef4\uff09\u201d\u96f6\u4ef6\u6784\u6210\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gregory_maksutov_telescope.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
