/*
 * si_solar_cell_with_ray_optics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class si_solar_cell_with_ray_optics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R", "0", "Reflectance of a perfect anti-reflective coating");
    model.param().set("ld", "0.5[um]", "Junction depth");
    model.param().set("R_series", "0[ohm]", "Series resistance");
    model.param()
         .set("irradianceModel", "1", "Irradiance model, 0 for Earth's oustise atmosphere, 1 for Earth surface");
    model.param().set("AU", "1.496E11[m]", "Astronomical units");
    model.param().set("Area", "1[cm^2]", "Cell exposed surface area in cm^2");
    model.param().set("tmax", "1[m]/c_const", "Simulation time");
    model.param().set("lcell", "10[um]", "Simulation thickness");
    model.param().set("H0", "1353[W/m^2]", "Reference irradiance (solar constant)");
    model.param().set("R_shunt", "1e12[ohm]", "Shunt tesistance");
    model.param().set("p_doping", "1e19[1/cm^3]", "Peak emitter doping, p-type");
    model.param()
         .set("lambdaMin", "263.8[nm]", "Minimum wavelength from refractive index data. We consider that the cell  doesn't respond to wavelength smaller than lambdaMin.");
    model.param().set("T", "300[K]", "Cell temperature");
    model.param().set("aziCell", "5", "Cell azimuth angle in degree");
    model.param().set("Tsun", "5777[K]", "Effective temperature of the Sun");
    model.param().set("n_doping", "5e15[1/cm^3]", "Uniform base doping, n-type");
    model.param().set("nbrsec", "60*60*24", "The number of second in one day");
    model.param().set("altitude", "620[m]", "Altitude in  m");
    model.param().set("Rsun", "695700000[m]", "Radius of the Sun in m");
    model.param().set("tiltCell", "8", "Cell tilt angle in degree");
    model.param()
         .set("lambdaMax", "826.6[nm]", "Maximum wavelength from refractive index data. We consider that the cell  doesn't respond to wavelength larger than lambdaMax.");
    model.param().set("Hsun", "sigma_const*Tsun^4", "Blackbody irradiance of the Sun");

    model.variable().create("var1");
    model.variable("var1").set("R_load", "t*1[ohm/s]+1e-6[ohm]");
    model.variable("var1").descr("R_load", "Load resistance");
    model.variable("var1").set("Req", "1/(1/R_load+1/R_shunt)+R_series");
    model.variable("var1").descr("Req", "Equivalent resistance");
    model.variable("var1").label("Circuit");
    model.component("comp1").variable().create("var2");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var2").label("Sunlight Properties");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("evol", "c_const/1[m]*nbrsec*t", "Time of the day in seconds starting from the initial time");
    model.component("comp1").variable("var2").set("evolh", "evol/60/60", "Elapsed hours");
    model.component("comp1").variable("var2").set("doy", "gop.srad1.doy", "Day of the year");
    model.component("comp1").variable("var2").set("g", "(357.529+0.98560028*doy)*pi/180", "Mean anomaly of the Sun");
    model.component("comp1").variable("var2")
         .set("q", "(280.459+0.98564736*doy)*pi/180", "Mean longitude of the Sun");
    model.component("comp1").variable("var2")
         .set("Dsun", "(1.00014-0.01671*cos(g)-0.00014*cos(2*g))*AU", "Distance of the Sun from Earth: from http://aa.usno.navy.mil/faq/docs/SunApprox.php");
    model.component("comp1").variable("var2")
         .set("Id_sd", "Hsun*Rsun^2/Dsun^2*(abs(gop.srad1.zenith)<pi/2)", "Sun distance direct irradiance");
    model.component("comp1").variable("var2")
         .set("AM", "if(abs(gop.srad1.zenith)<pi/2,1/(cos(gop.srad1.zenith)+0.50572*(96.07995-gop.srad1.zenith)^(-1.6364)),0)", "Air mass");
    model.component("comp1").variable("var2")
         .set("Id_am", "1353[W/m^2]*((1-0.14[1/m]*altitude/1000)*0.7^(AM^0.678)+0.14[1/m]*altitude/1000)*(abs(gop.srad1.zenith)<pi/2)", "Air mass direct irradiance");
    model.component("comp1").variable("var2")
         .set("nxCell", "-cos(aziCell*pi/180)*sin(tiltCell*pi/180)", "North component of the cell");
    model.component("comp1").variable("var2")
         .set("nyCell", "sin(aziCell*pi/180)*sin(tiltCell*pi/180)", "East component of the cell");
    model.component("comp1").variable("var2").set("nzCell", "-cos(tiltCell*pi/180)", "Up component of the cell");
    model.component("comp1").variable("var2")
         .set("nNormCell", "sqrt(nxCell^2+nyCell^2+nzCell^2)", "Norm of the cell direction");
    model.component("comp1").variable("var2")
         .set("n1", "nxCell/nNormCell", "Normalized North component of the cell");
    model.component("comp1").variable("var2").set("n2", "nyCell/nNormCell", "Normalized East component of the cell");
    model.component("comp1").variable("var2").set("n3", "nzCell/nNormCell", "Normalized Up component of the cell");
    model.component("comp1").variable("var2")
         .set("nDot", "n1*gop.srad1.nsolx+n2*gop.srad1.nsoly+n3*gop.srad1.nsolz", "Projection of the cell direction over the sun direction");
    model.component("comp1").variable("var2")
         .set("Mul", "if(irradianceModel==0,Id_sd,Id_am)*nDot/H0*(nDot>=0)", "Multiplication factor used to normalize the I-V and P-V data");
    model.component("comp1").variable("var2")
         .set("incidentAngle", "acos(nDot)", "IncidentAngle (angle between the Sun and cell directions)");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").create("srad1", "SolarRadiation", -1);
    model.component("comp1").physics("gop").feature("srad1").set("LocationDefinedBy", "City");
    model.component("comp1").physics("gop").feature("srad1").set("Date", new String[]{"01", "10", "2016"});
    model.component("comp1").physics("gop").feature("srad1").set("LocalTime", new String[]{"0", "0", "evol"});
    model.component("comp1").physics("gop").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("gop").feature("ge1").setIndex("name", "intMul", 0, 0);
    model.component("comp1").physics("gop").feature("ge1").setIndex("equation", "intMult-Mul", 0, 0);
    model.component("comp1").physics("gop").feature("ge1")
         .setIndex("description", "Integral of the multiplication factor", 0, 0);
    model.component("comp1").physics("gop").feature("ge1").set("DependentVariableQuantity", "time");

    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "range(0,1/48,1)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("Sun Position");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Elapsed hours");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Angle (deg)");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "gop.srad1.azimuth", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "gop.srad1.zenith", 1);
    model.result("pg1").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "evolh");
    model.result("pg1").feature("glob1").set("linestyle", "none");
    model.result("pg1").feature("glob1").set("linemarker", "circle");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Air Mass");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Elapsed hours");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Air Mass");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", -0.24);
    model.result("pg2").set("xmax", 24.24);
    model.result("pg2").set("ymin", 0.9);
    model.result("pg2").set("ymax", 10);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1")
         .setIndex("expr", "if((abs(gop.srad1.zenith)<pi/2)&&(AM<20)&&(evolh<12),AM,nan)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Air Mass", 0);
    model.result("pg2").feature("glob1")
         .setIndex("expr", "if((abs(gop.srad1.zenith)<pi/2)&&(AM<20)&&(evolh>=12),AM,nan)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "Air Mass", 1);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "evolh");
    model.result("pg2").feature("glob1").set("linecolor", "black");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("linemarker", "circle");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Direct Radiation");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Elapsed hours");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Direct irradiance (W/m<sup>2</sup>)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "if(irradianceModel==0,Id_sd,Id_am)*nDot*(nDot>=0)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "evolh");
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "From air mass", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "From Sun distance", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Incident Angle");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Elapsed hours");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Incident Angle (\u00b0)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1")
         .setIndex("expr", "if((incidentAngle<=pi/2)&&(evolh>=12),incidentAngle,nan)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "IncidentAngle", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "evolh");
    model.result("pg4").feature("glob1").set("linecolor", "blue");
    model.result("pg4").feature("glob1").set("linemarker", "circle");
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2")
         .setIndex("expr", "if((incidentAngle<=pi/2)&&(evolh<12),incidentAngle,nan)", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "deg", 0);
    model.result("pg4").feature("glob2").set("xdata", "expr");
    model.result("pg4").feature("glob2").set("xdataexpr", "evolh");
    model.result("pg4").feature("glob2").set("linecolor", "blue");
    model.result("pg4").feature("glob2").set("linemarker", "circle");
    model.result("pg4").run();
    model.result("pg4").create("glob3", "Global");
    model.result("pg4").feature("glob3").set("markerpos", "datapoints");
    model.result("pg4").feature("glob3").set("linewidth", "preference");
    model.result("pg4").feature("glob3").setIndex("expr", 90, 0);
    model.result("pg4").feature("glob3").set("linestyle", "dashed");
    model.result("pg4").feature("glob3").set("linecolor", "black");
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 1);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("i1", "Interval");
    model.component("comp2").geom("geom2").feature("i1").setIndex("coord", "lcell", 1);

    model.component("comp2").variable().create("var3");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").variable("var3").label("Photogeneration");
    model.component("comp2").variable("var3").selection().geom("geom2", 1);
    model.component("comp2").variable("var3").selection().set(1);
    model.component("comp2").variable("var3")
         .set("G", "4*pi*H0/(h_const*c_const)*integrate(kref(T,lambda)*F(lambda)*(1-R)*exp(-4*pi*kref(T,lambda)*x/lambda),lambda,lambdaMin,lambdaMax)");
    model.component("comp2").variable("var3").set("Pin", "H0");
    model.component("comp2").variable().create("var4");
    model.component("comp2").variable("var4").label("Current, Voltage  and Power");
    model.component("comp2").variable("var4").set("Vc", "if(t>=1e-3,Vode,nan)");
    model.component("comp2").variable("var4").descr("Vc", "Cathode Voltage");
    model.component("comp2").variable("var4").set("I", "if(t>=1e-3,semi.I0_1,nan)");
    model.component("comp2").variable("var4").descr("I", "Current");
    model.component("comp2").variable("var4").set("P", "if(t>=1e-3,semi.I0_1*Vode,nan)");
    model.component("comp2").variable("var4").descr("P", "Power");
    model.component("comp2").variable("var4").set("V_load", "Vc/(1+R_series*(1/R_load+1/R_shunt))");
    model.component("comp2").variable("var4").descr("V_load", "Output Voltage");
    model.component("comp2").variable("var4").set("I_load", "I-V_load/R_shunt");
    model.component("comp2").variable("var4").descr("I_load", "Output Current");
    model.component("comp2").variable("var4").set("P_load", "I_load*V_load");
    model.component("comp2").variable("var4").descr("P_load", "Output Power");

    model.component("comp2").func().create("int1", "Interpolation");
    model.component("comp2").func("int1").label("Refractive index, real part");
    model.component("comp2").func("int1").set("source", "file");
    model.component("comp2").func("int1").set("filename", "si_solar_cell_with_ray_optics_embedded_nref.txt");
    model.component("comp2").func("int1").importData();
    model.component("comp2").func("int1").setIndex("argunit", "K", 0);
    model.component("comp2").func("int1").setIndex("argunit", "m", 1);
    model.component("comp2").func("int1").setEntry("funcnames", "col3", "nref");
    model.component("comp2").func("int1").setIndex("fununit", "1", 0);
    model.component("comp2").func().create("int2", "Interpolation");
    model.component("comp2").func("int2").label("Refractive index, imaginary part");
    model.component("comp2").func("int2").set("source", "file");
    model.component("comp2").func("int2").set("filename", "si_solar_cell_with_ray_optics_embedded_kref.txt");
    model.component("comp2").func("int2").importData();
    model.component("comp2").func("int2").setIndex("argunit", "K", 0);
    model.component("comp2").func("int2").setIndex("argunit", "m", 1);
    model.component("comp2").func("int2").setEntry("funcnames", "col3", "kref");
    model.component("comp2").func("int2").setIndex("fununit", "1", 0);
    model.component("comp2").func().create("an1", "Analytic");
    model.component("comp2").func("an1")
         .label("Spectral irradiance (Plank's law) normalized by Hsun = sigma*Tsun^4");
    model.component("comp2").func("an1").set("funcname", "F");
    model.component("comp2").func("an1")
         .set("expr", "1/Hsun*2*pi*h_const*c_const^2/(lambda^5*(exp(h_const*c_const/(k_B_const*lambda*Tsun))-1.0))");
    model.component("comp2").func("an1").set("args", "lambda");
    model.component("comp2").func("an1").setIndex("argunit", "m", 0);
    model.component("comp2").func("an1").set("fununit", "1/m");
    model.component("comp2").func("an1").setIndex("plotargs", "lambdaMin", 0, 1);
    model.component("comp2").func("an1").setIndex("plotargs", "lambdaMax", 0, 2);

    model.component("comp2").physics().create("semi", "Semiconductor", "geom2");

    model.study("std1").feature("rtrac").setSolveFor("/physics/semi", false);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("AroraMobilityModel", "AroraMobilityModel", "Arora mobility model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("PowerLawMobilityModel", "PowerLawMobilityModel", "Power law mobility model");
    model.component("comp2").material("mat2").propertyGroup().create("Auger", "Auger", "Auger recombination");
    model.component("comp2").material("mat2").propertyGroup().create("Direct", "Direct", "Direct recombination");
    model.component("comp2").material("mat2").propertyGroup()
         .create("SRH", "SRH", "Shockley\u2013Read\u2013Hall recombination");
    model.component("comp2").material("mat2").propertyGroup()
         .create("FletcherMobilityModel", "FletcherMobilityModel", "Fletcher mobility model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("CaugheyThomasMobilityModel", "CaugheyThomasMobilityModel", "Caughey\u2013Thomas mobility model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp2").material("mat2").propertyGroup()
         .create("LombardiSurfaceMobilityModel", "LombardiSurfaceMobilityModel", "Lombardi surface mobility model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ImpactIonization", "ImpactIonization", "Impact ionization");
    model.component("comp2").material("mat2").propertyGroup()
         .create("SlotboomModel", "SlotboomModel", "Slotboom model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("JainRoulstonModel", "JainRoulstonModel", "Jain\u2013Roulston model");
    model.component("comp2").material("mat2").propertyGroup()
         .create("KlaassenUnifiedMobilityModel", "KlaassenUnifiedMobilityModel", "Klaassen unified mobility model");
    model.component("comp2").material("mat2").label("Si - Silicon");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]", "0", "0", "0", "131[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("mun0_ref_arora", "1252[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("mup0_ref_arora", "407[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("mun_min_ref_arora", "88[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("mup_min_ref_arora", "54.3[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("Nn0_ref_arora", "1.26e17[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel")
         .set("Np0_ref_arora", "2.35e17[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("alpha0_arora", "0.88");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("beta1_arora", "-0.57");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("beta2_arora", "-2.33");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("beta3_arora", "-2.33");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("beta4_arora", "-0.146");
    model.component("comp2").material("mat2").propertyGroup("AroraMobilityModel").set("Tref_arora", "300[K]");
    model.component("comp2").material("mat2").propertyGroup("PowerLawMobilityModel")
         .set("mun0_pl", "1448[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("PowerLawMobilityModel")
         .set("mup0_pl", "473[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("PowerLawMobilityModel").set("alphan_pl", "2.33");
    model.component("comp2").material("mat2").propertyGroup("PowerLawMobilityModel").set("alphap_pl", "2.23");
    model.component("comp2").material("mat2").propertyGroup("PowerLawMobilityModel").set("Tref_pl", "300[K]");
    model.component("comp2").material("mat2").propertyGroup("Auger").set("Cn", "2.8e-31[cm^6/s]");
    model.component("comp2").material("mat2").propertyGroup("Auger").set("Cp", "9.9e-32[cm^6/s]");
    model.component("comp2").material("mat2").propertyGroup("Direct").set("C", "0[cm^3/s]");
    model.component("comp2").material("mat2").propertyGroup("SRH").set("taun", "10[us]");
    model.component("comp2").material("mat2").propertyGroup("SRH").set("taup", "10[us]");
    model.component("comp2").material("mat2").propertyGroup("FletcherMobilityModel")
         .set("F1_fl", "1.04e21[1/(cm^1*V*s)]");
    model.component("comp2").material("mat2").propertyGroup("FletcherMobilityModel").set("F2_fl", "7.45e13[1/cm^2]");
    model.component("comp2").material("mat2").propertyGroup("FletcherMobilityModel").set("Tref_fl", "300[K]");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel")
         .label("Caughey\u2013Thomas mobility model");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("alphan0_ct", "1.11");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("alphap0_ct", "1.21");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("vn0_ct", "1e7[cm/s]");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel")
         .set("vp0_ct", "8.37e6[cm/s]");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("betan1_ct", "0.66");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("betap1_ct", "0.17");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("betan2_ct", "-0.87");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("betap2_ct", "-0.52");
    model.component("comp2").material("mat2").propertyGroup("CaugheyThomasMobilityModel").set("Tref_ct", "300[K]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial").set("Eg0", "1.12[V]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial").set("chi0", "4.05[V]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/300[K])^(3/2)*1.04e19[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial")
         .set("Nc", "(T/300[K])^(3/2)*2.8e19[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial").set("mun", "1450[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial").set("mup", "500[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("SemicondMaterial").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltan_ls", "5.82e14[V/s]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("deltap_ls", "2.05e14[V/s]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun1_ls", "4.75e7[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup1_ls", "9.93e7[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mun2_ls", "1.74e5[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("mup2_ls", "8.84e5[cm^2/(V*s)]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphan_ls", "0.125");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("alphap_ls", "0.0317");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel").set("Tref_ls", "1[K]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Eref_ls", "1[V/cm]");
    model.component("comp2").material("mat2").propertyGroup("LombardiSurfaceMobilityModel")
         .set("Nref_ls", "1[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("an", "0.426[1/V]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("ap", "0.243[1/V]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("bn", "4.81E5[V/cm]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("bp", "6.53E5[V/cm]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("cnii", "3.05E-4[1/K]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("cpii", "5.35E-4[1/K]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("dn", "6.86E-4[1/K]");
    model.component("comp2").material("mat2").propertyGroup("ImpactIonization").set("dp", "5.67E-4[1/K]");
    model.component("comp2").material("mat2").propertyGroup("SlotboomModel").set("Eref_sb", "0.00692[V]");
    model.component("comp2").material("mat2").propertyGroup("SlotboomModel").set("Nref_sb", "1.3e17[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("SlotboomModel").set("alpha_sb", "0.5");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("An_jr", "3.5e-8[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Bn_jr", "0[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Cn_jr", "0[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Ap_jr", "3.5e-8[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Bp_jr", "0[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Cp_jr", "0[V]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("Nref_jr", "1[1/cm^3]");
    model.component("comp2").material("mat2").propertyGroup("JainRoulstonModel").set("alpha_jr", "0.5");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("T_ref_kl", "300[K]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_max_kl", "1414.0[cm^2/V/s]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_max_kl", "470.5[cm^2/V/s]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_e_min_kl", "68.5[cm^2/V/s]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("mu_h_min_kl", "44.9[cm^2/V/s]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_e_kl", "2.285");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("theta_h_kl", "2.247");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_e_1_kl", "0.711");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("alpha_h_1_kl", "0.719");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_e_1_kl", "9.20e16[cm^-3]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_h_1_kl", "2.23e17[cm^-3]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("c_D_kl", "0.21");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("c_A_kl", "0.50");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_D_kl", "4.0e20[cm^-3]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_ref_A_kl", "7.2e20[cm^-3]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("f_BH_kl", "3.828");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("f_CW_kl", "2.459");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("N_BH_kl", "1.36e20[cm^-3]");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("P_CW_kl", "3.97e13");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_1_kl", "0.89233");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_2_kl", "0.41372");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_3_kl", "0.19778");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_4_kl", "0.28227");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("s_5_kl", "0.005978");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_6_kl", "1.80618");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("s_7_kl", "0.72169");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("r_1_kl", "0.7643");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("r_2_kl", "2.2999");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("r_3_kl", "6.5502");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("r_4_kl", "2.3670");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("r_5_kl", "-0.01552");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel").set("r_6_kl", "0.6478");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_e_kl", "me_const");
    model.component("comp2").material("mat2").propertyGroup("KlaassenUnifiedMobilityModel")
         .set("m_h_kl", "1.258*me_const");

    model.component("comp2").physics("semi").prop("d").set("A", "Area");
    model.component("comp2").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp2").physics("semi").feature("smm1").set("minput_temperature", "T");
    model.component("comp2").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp2").physics("semi").feature("adm1").selection().all();
    model.component("comp2").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp2").physics("semi").feature("adm1").set("NDc", "n_doping");
    model.component("comp2").physics("semi").create("gdm1", "GeometricDopingModel", 1);
    model.component("comp2").physics("semi").feature("gdm1").selection().all();
    model.component("comp2").physics("semi").feature("gdm1").set("NAgen", "p_doping");
    model.component("comp2").physics("semi").feature("gdm1").set("jd_gen", "ld");
    model.component("comp2").physics("semi").feature("gdm1").set("Nb_gen_src", "root.comp2.semi.adm1.NDc");
    model.component("comp2").physics("semi").feature("gdm1").feature("gdmbs1").selection().set(1);
    model.component("comp2").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp2").physics("semi").feature("tar1").selection().all();
    model.component("comp2").physics("semi").create("udg1", "UDGeneration", 1);
    model.component("comp2").physics("semi").feature("udg1").selection().all();
    model.component("comp2").physics("semi").feature("udg1").set("Gn", "G");
    model.component("comp2").physics("semi").feature("udg1").set("Gp", "G");
    model.component("comp2").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp2").physics("semi").feature("mc1").selection().set(2);
    model.component("comp2").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp2").physics("semi").feature("mc2").selection().set(1);
    model.component("comp2").physics("semi").feature("mc2").set("V0", "Vode");
    model.component("comp2").physics("semi").create("ge1", "GlobalEquations", -1);
    model.component("comp2").physics("semi").feature("ge1").setIndex("name", "Vode", 0, 0);
    model.component("comp2").physics("semi").feature("ge1").setIndex("equation", "Vode-semi.I0_1*Req", 0, 0);
    model.component("comp2").physics("semi").feature("ge1").setIndex("description", "Cathode voltage", 0, 0);
    model.component("comp2").physics("semi").feature("ge1").set("DependentVariableQuantity", "electricpotential");
    model.component("comp2").physics("semi").feature("ge1").set("SourceTermQuantity", "electricpotential");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/gop", false);
    model.study("std2").feature("time").setSolveFor("/physics/semi", true);
    model.study("std2").feature("time").set("tlist", "0 0.9e-3, 10^{range(-3,8/50,5)}");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol2").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol2").feature("t1").set("initialstepbdf", "1e-10");
    model.sol("sol2").feature("t1").set("maxorder", 2);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("I-V Curve");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Voltage (V)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Current (mA)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "I_load", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "mA", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "V_load");
    model.result("pg5").feature("glob1").set("linemarker", "circle");
    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("P-V Curve");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Voltage (V)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Power (mW)");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "P_load", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "mW", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "V_load");
    model.result("pg6").feature("glob1").set("linemarker", "circle");
    model.result("pg6").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Day of the year and Distance from the Sun");
    model.result().numerical("gev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev1").setIndex("expr", "gop.srad1.doy+0.5", 0);
    model.result().numerical("gev1").setIndex("expr", "Dsun", 1);
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Irradiance");
    model.result().numerical("gev2").setIndex("expr", "Id_sd", 0);
    model.result().numerical("gev2").setIndex("expr", "Id_am", 1);
    model.result().numerical("gev2").setIndex("expr", "AM", 2);
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Multiplication Factor");
    model.result().numerical("gev3").setIndex("expr", "evolh", 0);
    model.result().numerical("gev3").setIndex("expr", "Mul", 1);
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Normalized Integration of the Multiplication Factor");
    model.result().numerical("gev4").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev4").setIndex("expr", "intMul/tmax", 0);
    model.result().numerical("gev4").setIndex("unit", "", 0);
    model.result().numerical("gev4").setIndex("descr", "Efficiency compared to Id = H0 over one day", 0);
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").set("data", "dset3");
    model.result().numerical("gev5").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev5").label("Maxima");
    model.result().numerical("gev5").setIndex("expr", "attimemax(1e-3,1e5,P_load,V_load)", 0);
    model.result().numerical("gev5").setIndex("expr", "timemax(1e-3,1e5,P_load)", 1);
    model.result().numerical("gev5").setIndex("expr", "timemax(1e-3,1e5,I_load)", 2);
    model.result().numerical("gev5").setIndex("expr", "V_load", 3);
    model.result().numerical("gev5").setIndex("expr", "at(1e-3,I_load)", 4);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Day of the year and Distance from the Sun");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Irradiance");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Multiplication Factor");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("Normalized Integration of the Multiplication Factor");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("Maxima");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();

    model
         .title("\u57fa\u4e8e\u5c04\u7ebf\u5149\u5b66\u6a21\u62df\u7845\u592a\u9633\u80fd\u7535\u6c60\uff08\u5185\u5d4c\u6a21\u578b\uff09");

    model
         .description("\u672c App \u7ed3\u5408\u4f7f\u7528\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u4e0e\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\u6765\u6f14\u793a\u7845\u592a\u9633\u80fd\u7535\u6c60\u5728\u7528\u6237\u6307\u5b9a\u4f4d\u7f6e\u7684\u5de5\u4f5c\u60c5\u51b5\u3002\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u8ba1\u7b97\u4e00\u5e74\u4e2d\u7684\u67d0\u4e00\u5929\u3001\u5728\u7528\u6237\u9009\u5b9a\u4f4d\u7f6e\u7684\u5e73\u5747\u7167\u5ea6\u3002\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\u6839\u636e\u7528\u6237\u6307\u5b9a\u7684\u8bbe\u8ba1\u53c2\u6570\u6765\u8ba1\u7b97\u592a\u9633\u80fd\u7535\u6c60\u7684\u5f52\u4e00\u5316\u8f93\u51fa\u7279\u6027\u3002\u7136\u540e\uff0c\u901a\u8fc7\u5c06\u5f52\u4e00\u5316\u8f93\u51fa\u7279\u6027\u4e58\u4ee5\u8ba1\u7b97\u5f97\u5230\u7684\u5e73\u5747\u7167\u5ea6\uff0c\u53ef\u4ee5\u5f97\u5230\u7535\u6c60\u5728\u6307\u5b9a\u65e5\u671f\u548c\u4f4d\u7f6e\u7684\u8f93\u51fa\u7279\u6027\uff0c\u5176\u4e2d\u5047\u8bbe\u8f93\u51fa\u4e0e\u7167\u5ea6\u4e4b\u95f4\u4e3a\u7b80\u5355\u7684\u7ebf\u6027\u5173\u7cfb\u3002");

    model.label("si_solar_cell_with_ray_optics_embedded.mph");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("filename", "user:///solar_Cell");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u592a\u9633\u80fd\u7535\u6c60\u8bbe\u8ba1\u5668");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u672c App \u7528\u4e8e\u8bbe\u8ba1\u5b9e\u9645\u592a\u9633\u8f90\u7167\u6761\u4ef6\u4e0b\u7684\u7845\u592a\u9633\u80fd\u7535\u6c60\u7684\u57fa\u7840\u7ed3\u6784\u3002");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u5c04\u7ebf\u5149\u5b66\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").label("\u534a\u5bfc\u4f53\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("std1").set("noderef", "std2");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570 1");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").label("\u592a\u9633\u4f4d\u7f6e");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").label("\u76f4\u63a5\u8f90\u5c04");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").label("\u5165\u5c04\u89d2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("pg1")
         .set("noderef", "pg4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4").label("I-V \u66f2\u7ebf");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec5").label("P-V \u66f2\u7ebf");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec5").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec5").feature("pg1")
         .set("noderef", "pg6");

    model.component("comp1").physics("gop").feature("srad1").set("LocationDefinedBy", "City");

    model.title("\u57fa\u4e8e\u5c04\u7ebf\u5149\u5b66\u6a21\u62df\u7845\u592a\u9633\u80fd\u7535\u6c60");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5355\u4e2a App \u4e2d\u7684\u591a\u4e2a\u7ec4\u4ef6\uff08\u4e00\u7ef4\u548c\u4e09\u7ef4\uff09\n\u2022 \u5982\u540c\u5728\u6a21\u578b\u4e2d\u4f7f\u7528\u201c\u6570\u636e\u8bbf\u95ee\u201d\u529f\u80fd\u4e00\u6837\uff0c\u5728 App \u4e2d\u4f7f\u7528\u76f8\u540c\u7684\u9009\u62e9\u5217\u8868\n\u2022 \u4f7f\u7528\u7ec4\u5408\u6846\u8f93\u51fa\u7279\u5b9a\u65f6\u6b65\u7684\u6570\u503c\u7ed3\u679c\n\n\u8be5 App \u7ed3\u5408\u4f7f\u7528\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u4e0e\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\u6765\u6f14\u793a\u7845\u592a\u9633\u80fd\u7535\u6c60\u5728\u7528\u6237\u6307\u5b9a\u4f4d\u7f6e\u7684\u5de5\u4f5c\u60c5\u51b5\u3002\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u7528\u4e8e\u8ba1\u7b97\u4e00\u5e74\u4e2d\u4e00\u5929\u7684\u5e73\u5747\u7167\u5ea6\u3002\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\u57fa\u4e8e\u7528\u6237\u6307\u5b9a\u7684\u8bbe\u8ba1\u53c2\u6570\u6765\u8ba1\u7b97\u592a\u9633\u80fd\u7535\u6c60\u7684\u5f52\u4e00\u5316\u8f93\u51fa\u7279\u6027\u3002\u7136\u540e\uff0c\u901a\u8fc7\u5c06\u5f52\u4e00\u5316\u8f93\u51fa\u7279\u6027\u4e58\u4ee5\u8ba1\u7b97\u51fa\u7684\u5e73\u5747\u7167\u5ea6\uff0c\u5f97\u5230\u7535\u6c60\u5728\u6307\u5b9a\u65e5\u671f\u548c\u4f4d\u7f6e\u7684\u8f93\u51fa\u7279\u6027\uff0c\u5176\u4e2d\u5047\u8bbe\u8f93\u51fa\u4e0e\u7167\u5ea6\u4e4b\u95f4\u5b58\u5728\u7b80\u5355\u7684\u7ebf\u6027\u5173\u7cfb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("si_solar_cell_with_ray_optics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
